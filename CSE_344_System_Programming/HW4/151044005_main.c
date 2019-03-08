#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <semaphore.h> 
#include <fcntl.h> 
#include <signal.h>

#define CHILDS 6
#define SHM_KEY 99999
#define ELEMENTS_SIZE 4
#define NEED_SIZE 2

static volatile int looper = 1;
sem_t *sem, *sem2;
int shmid;
char* allElements[ELEMENTS_SIZE] = {"butter", "sugar", "flour", "eggs"};
char* zero_child[NEED_SIZE] = {"butter", "sugar"};
char* first_child[NEED_SIZE] = {"flour", "butter"};
char* second_child[NEED_SIZE] = {"eggs", "flour"};
char* third_child[NEED_SIZE] = {"butter", "eggs"};
char* fourth_child[NEED_SIZE] = {"eggs", "sugar"};
char* fifth_child[NEED_SIZE] = {"flour", "sugar"};

void sigtermCatch();
void sigtermCatchChild();
int isItNeeded(char** neededArr, int* givenArr);
int whichChild(int* givenArr);
void whoIsWaiting(int chef);

int main(int argc, char* argv[]){

    pid_t childpid;
    
    int i;
    int chefNumber;
    int* shmArr;
    
    key_t key = SHM_KEY;
    sigset_t intmask;
    struct sigaction action;
    struct sigaction actionChild;
    struct timespec times;
    int firstRand, secondRand;

    memset(&action, 0, sizeof(action));
    action.sa_handler = sigtermCatch;
    if(sigaction(SIGINT, &action, NULL) == -1){
        perror("Error.\n");
        return -1;
    }

    if ((sigemptyset(&intmask) == -1) || (sigaddset(&intmask, SIGINT) == -1)){
        perror("Error.\n");
        return -1;
    }   
    
    clock_gettime(CLOCK_REALTIME,&times);

	srand(times.tv_nsec);

    sem = sem_open("sem", O_CREAT|O_EXCL, 0, 0);
    sem2 = sem_open("sem2", O_CREAT|O_EXCL, 0, 1);
    
    sem_unlink("sem");
    sem_unlink("sem2");

    for(i = 0; i < CHILDS; i++){
        childpid = fork();

        if(childpid < 0){
            perror("Fork failed.\n");
        }

        else if(childpid == 0){
            break;
        }

    }                                                            
    
    if(childpid == 0){

        memset(&actionChild, 0, sizeof(actionChild));
        actionChild.sa_handler = sigtermCatchChild;
        if(sigaction(SIGINT, &actionChild, NULL) == -1){
            perror("Error.\n");
            return -1;
        }
        
        while(looper){

            whoIsWaiting(i);

            sem_wait(sem);

            if (sigprocmask(SIG_BLOCK, &intmask, NULL) == -1)
                perror("Error.\n");

            shmid = shmget(key, NEED_SIZE*sizeof(int), IPC_EXCL);

            shmArr = shmat(shmid, 0, SHM_RDONLY);

            chefNumber = whichChild(shmArr);

            printf("Chef %d has taken the %s\n", chefNumber, allElements[shmArr[0]]);
            printf("Chef %d has taken the %s\n", chefNumber, allElements[shmArr[1]]);
            printf("Chef %d is preparing the dessert\n", chefNumber);
            printf("Chef %d has delivered the dessert to the wholesaler\n", chefNumber);
            
            shmdt(shmArr);

            if (sigprocmask(SIG_UNBLOCK, &intmask, NULL) == -1)
                perror("Error.\n");

            sem_post(sem2);

            
            

        }
        printf("CHILD %d IS EXITING...\n", getpid());
        exit(0);
    }

    else{

        while(looper){


            printf("Wholesaler is waiting for the dessert\n");
            
            sem_wait(sem2);

            if (sigprocmask(SIG_BLOCK, &intmask, NULL) == -1)
                perror("Error.\n");
            

            printf("Wholesaler has obtained the dessert and left to sell it\n");
                
            shmid = shmget(key, NEED_SIZE*sizeof(int), IPC_CREAT | 0666);

            if(shmid == -1)
                perror("LOL.\n");
                
            shmArr = shmat(shmid, NULL, 0);

            if(shmArr == (int*)-1)
                printf("Error\n");

            while(1){
                firstRand = rand() % ELEMENTS_SIZE;
                secondRand = rand() % ELEMENTS_SIZE;
                if(firstRand != secondRand)
                    break;
            }
                
            shmArr[0] = firstRand;
            shmArr[1] = secondRand;

            printf("Wholesaler delivers %s and %s\n",  allElements[firstRand], allElements[secondRand]);
            shmdt(shmArr);

            if (sigprocmask(SIG_UNBLOCK, &intmask, NULL) == -1)
                perror("Error.\n");

            sem_post(sem);
        
        }
    
    }

    return 0;
}

void sigtermCatch(){
    while(wait(NULL) != -1);

    sem_close(sem);
    sem_close(sem2);

    shmctl(shmid,IPC_RMID,NULL);

    exit(0);
}

void sigtermCatchChild(){
    exit(0);
}

int isItNeeded(char** neededArr, int* givenArr){
    if((strcmp(neededArr[0], allElements[givenArr[0]]) == 0 && strcmp(neededArr[1],allElements[givenArr[1]]) == 0)||
    (strcmp(neededArr[0], allElements[givenArr[1]]) == 0 && strcmp(neededArr[1], allElements[givenArr[0]]) == 0))
        return 1;

    return 0;    
}

void whoIsWaiting(int chef){
    switch(chef){
        case 0: printf("Chef %d is waiting for %s and %s\n", chef+1, zero_child[0], zero_child[1]); break;
        case 1: printf("Chef %d is waiting for %s and %s\n", chef+1, first_child[0], first_child[1]); break;
        case 2: printf("Chef %d is waiting for %s and %s\n", chef+1, second_child[0], second_child[1]); break;
        case 3: printf("Chef %d is waiting for %s and %s\n", chef+1, third_child[0], third_child[1]); break;
        case 4: printf("Chef %d is waiting for %s and %s\n", chef+1, fourth_child[0], fourth_child[1]); break;
        case 5: printf("Chef %d is waiting for %s and %s\n", chef+1, fifth_child[0], fifth_child[1]); break;
    }
}

int whichChild(int* givenArr){
    if(isItNeeded(zero_child, givenArr))
        return 1;
    else if(isItNeeded(first_child, givenArr))
        return 2;
    else if(isItNeeded(second_child, givenArr))
        return 3;
    else if(isItNeeded(third_child, givenArr))
        return 4;
    else if(isItNeeded(fourth_child, givenArr))
        return 5;
    else if(isItNeeded(fifth_child, givenArr))
        return 6;

    return -1;    
}


