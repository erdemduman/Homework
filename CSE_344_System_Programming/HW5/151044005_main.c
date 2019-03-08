#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h> 
#include <signal.h>
#include <math.h>
#include <pthread.h>

#define FILENAMESIZE 50
#define SIZEOFFLORISTS 3
#define REQSIZE 1024

typedef struct fl{
	char name[50];
	int coord[2];
	double speed;
	char* flowers[3];
}Florist;

typedef struct req{
	char name[50];
	int coord[2];
	char request[50];
	int empty;
}Request;

int cond = 1;
pthread_mutex_t lock[3];
pthread_mutex_t childLocks[3];
pthread_t threads[3];

Florist florist[SIZEOFFLORISTS];
Request arr0[REQSIZE];
Request arr1[REQSIZE];
Request arr2[REQSIZE];
Request client;
char* line = NULL;

void parseFlorists(FILE* fptr);
void parseRequest();
int availableOne();
int contains(Florist f);
double distance(Request r ,Florist f);
void initArr(Request* clientArr);
void addIntoList(Request* clientArr, Request client);
void processIt(Request* clientArr);

void *myThreadFun(void* id){

	int* totalTime;
	double delTime;
	int preTime;
	double speedVal;
	struct timespec times;

	totalTime = (int*) malloc(sizeof(int));

	*totalTime = 0;

	while(1){
		if(cond == 0){
			if(*(int*)id == 0 && arr0[0].empty == 1)
				pthread_mutex_unlock(&childLocks[0]);
			
			else if(*(int*)id == 1 && arr1[0].empty == 1)
				pthread_mutex_unlock(&childLocks[1]);
			
			else if(*(int*)id == 2 && arr2[0].empty == 1)
				pthread_mutex_unlock(&childLocks[2]);
			
			else
				break;
		}

		if(*(int*)id == 0){
			pthread_mutex_lock(&childLocks[0]);
			if(arr0[0].empty == 1){
				clock_gettime(CLOCK_REALTIME,&times);
				srand(times.tv_nsec);
				preTime = rand() % 40;
				preTime += 10;
				delTime = distance(arr0[0], florist[*(int*)id])/florist[*(int*)id].speed;
				*totalTime += (int)(preTime + delTime);
				printf("Florist %s has delivered a %s to %s in %dms\n", florist[*(int*)id].name, arr0[0].request, arr0[0].name, (int)(preTime + delTime));
				processIt(arr0);
				fflush(stdout);
			}
			pthread_mutex_unlock(&lock[0]);
		}
			
		else if(*(int*)id == 1){
			pthread_mutex_lock(&childLocks[1]);
			if(arr1[0].empty == 1){
				clock_gettime(CLOCK_REALTIME,&times);
				srand(times.tv_nsec);
				preTime = rand() % 40;
				preTime += 10;
				delTime = distance(arr1[0], florist[*(int*)id])/florist[*(int*)id].speed;
				*totalTime += (int)(preTime + delTime);
				printf("Florist %s has delivered a %s to %s in %dms\n", florist[*(int*)id].name, arr1[0].request, arr1[0].name, (int)(preTime + delTime));
				processIt(arr1);
				fflush(stdout);
			}
			
			pthread_mutex_unlock(&lock[1]);
			
		}
			
		else if(*(int*)id == 2){
			pthread_mutex_lock(&childLocks[2]);
			if(arr2[0].empty == 1){
				clock_gettime(CLOCK_REALTIME,&times);
				srand(times.tv_nsec);
				preTime = rand() % 40;
				preTime += 10;
				delTime = distance(arr2[0], florist[*(int*)id])/florist[*(int*)id].speed;
				*totalTime += (int)(preTime + delTime);
				printf("Florist %s has delivered a %s to %s in %dms\n", florist[*(int*)id].name, arr2[0].request, arr2[0].name, (int)(preTime + delTime));
				processIt(arr2);
				fflush(stdout);
			}
			
			pthread_mutex_unlock(&lock[2]);
		
		}
	}

	printf("%s closing shop.\n", florist[*(int*)id].name);

    //pthread_exit(totalTime);
	return totalTime;
}

int main(int argc, char* argv[]){

	char filename[FILENAMESIZE];
	FILE* fptr;
	int looper;
	size_t len = 100;
	int tNames[3] = {0,1,2};
	void *totalOfZero, *totalOfOne, *totalOfTwo;
	int sale0, sale1, sale2;
	
	sale0 = 0;
	sale1 = 0;
	sale2 = 0;

	if(argc != 2){
		fprintf(stderr, "Usage: ./exe [filename]\n");
	}

	else{

		initArr(arr0);
		initArr(arr1);
		initArr(arr2);

		if (pthread_mutex_init(&lock[0], NULL) != 0){
			printf("Mutex Fail.\n");
			return 1;
		}
		
		if (pthread_mutex_init(&lock[1], NULL) != 0){
			printf("Mutex Fail.\n");
			return 1;
		}
		
		if (pthread_mutex_init(&lock[2], NULL) != 0){
			printf("Mutex Fail\n");
			return 1;
		}

		if (pthread_mutex_init(&childLocks[0], NULL) != 0){
			printf("Mutex Fail\n");
			return 1;
		}

		if (pthread_mutex_init(&childLocks[1], NULL) != 0){
			printf("Mutex Fail\n");
			return 1;
		}

		if (pthread_mutex_init(&childLocks[2], NULL) != 0){
			printf("Mutex Fail\n");
			return 1;
		}
		
		pthread_mutex_lock(&childLocks[0]);
			
		pthread_mutex_lock(&childLocks[1]);
			
		pthread_mutex_lock(&childLocks[2]);
			

		for(looper = 0; looper < 3; looper++){
			if(pthread_create(&threads[looper], NULL, myThreadFun, (void*)&tNames[looper]) == -1){
				perror("There is an error.\n");
				exit(1);
			}
		}

		fptr = fopen(argv[1], "r");

		parseFlorists(fptr);

		while(getline(&line, &len, fptr) != -1){

			
			
			parseRequest();
			
			if(availableOne() == 0){
				pthread_mutex_lock(&lock[0]);
				addIntoList(arr0, client);
				sale0++;
				pthread_mutex_unlock(&childLocks[0]);
				
			}

			else if(availableOne() == 1){
				pthread_mutex_lock(&lock[1]);
				addIntoList(arr1, client);
				sale1++;
				pthread_mutex_unlock(&childLocks[1]);
			}

			else if(availableOne() == 2){
				pthread_mutex_lock(&lock[2]);
				addIntoList(arr2, client);
				sale2++;
				pthread_mutex_unlock(&childLocks[2]);
			}

		}

		cond = 0;

		pthread_mutex_unlock(&childLocks[0]);
		pthread_mutex_unlock(&childLocks[1]);
		pthread_mutex_unlock(&childLocks[2]);	

		pthread_join(threads[0], &totalOfZero);
		pthread_join(threads[1], &totalOfOne);
		pthread_join(threads[2], &totalOfTwo);

		pthread_detach(threads[0]);
		pthread_detach(threads[1]);
		pthread_detach(threads[2]);

		printf("\n");
		printf("Sale statistic for today:\n");
		printf("------------------------------------------------------------\n");
		printf("Florist                # of sales                Total time\n");
		printf("------------------------------------------------------------\n");
		printf("%s                      %d                        %dms\n", florist[0].name, sale0, *(int*)totalOfZero);
		printf("%s                      %d                        %dms\n", florist[1].name, sale1, *(int*)totalOfOne);
		printf("%s                      %d                        %dms\n\n", florist[2].name, sale2, *(int*)totalOfTwo);

		fclose(fptr);
		free((int*)totalOfZero);
		free((int*)totalOfOne);
		free((int*)totalOfTwo);

		pthread_mutex_destroy(&lock[0]);
		pthread_mutex_destroy(&lock[1]);
		pthread_mutex_destroy(&lock[2]);
		pthread_mutex_destroy(&childLocks[0]);
		pthread_mutex_destroy(&childLocks[1]);
		pthread_mutex_destroy(&childLocks[2]);
	}

	free(line);

	return 0;
}

void parseFlorists(FILE* fptr){
	
	int counter;
	char* line = NULL;
	size_t len = 100;

	for(counter = 0; counter < SIZEOFFLORISTS; counter++){
		getline(&line, &len, fptr);
    }

	getline(&line, &len, fptr);

	strcpy(florist[0].name, "Ayse");
	florist[0].coord[0] = 10;
	florist[0].coord[1] = 25;
	florist[0].speed = 1.5;
	florist[0].flowers[0] = "orchid";
	florist[0].flowers[1] = "rose";
	florist[0].flowers[2] = "violet";

	strcpy(florist[1].name, "Fatma");
	florist[1].coord[0] = -10;
	florist[1].coord[1] = -15;
	florist[1].speed = 1.3;
	florist[1].flowers[0] = "clove";
	florist[1].flowers[1] = "rose";
	florist[1].flowers[2] = "daffodil";

	strcpy(florist[2].name, "Murat");
	florist[2].coord[0] = -10;
	florist[2].coord[1] = 8;
	florist[2].speed = 1.5;
	florist[2].flowers[0] = "violet";
	florist[2].flowers[1] = "daffodil";
	florist[2].flowers[2] = "orchid";

	free(line);
}

void parseRequest(){

	char* tokens[8]; 
	char* token;
	int i;
	int coordCounter = 1;
	int xPosStr = 0;
	int yPosStr = 0;
	char coordX[16];
	char coordY[16];

	memset(coordX, '\0', sizeof(coordX));
	memset(coordY, '\0', sizeof(coordY));

	token = strtok(line, " ");
    for(i = 0; token != NULL ; i++){
        tokens[i] = token;
        token = strtok(NULL, " ");
    }

	tokens[2][strlen(tokens[2])-1] = '\0';

	strcpy(client.name, tokens[0]);

	while(1){
		
		if(tokens[1][coordCounter] == ')'){
			yPosStr = coordCounter;
			break;
		}

		if(tokens[1][coordCounter] == ',')
			xPosStr = coordCounter;
		
		coordCounter++;
	}

	strncpy(coordX, tokens[1] + 1, xPosStr - 1);
	strncpy(coordY, tokens[1] + xPosStr + 1, yPosStr - xPosStr - 1);

	client.coord[0] = atoi(coordX);
	client.coord[1] = atoi(coordY);

	strcpy(client.request, tokens[2]);

	client.empty = 1;

	//free(token);
}

int availableOne(){
	int i;
	int theOne;
	int result = __INT_MAX__;

	for(i = 0; i < SIZEOFFLORISTS; i++){
		if(contains(florist[i])){
			if(result > distance (client ,florist[i])){
				result = distance(client, florist[i]);
				theOne = i;
			}
		}
	}
	
	return theOne;
}

int contains(Florist f){
	int i;
	int founded = 0;
	
	for(i = 0; i < 3; i++){
		if(strcmp(f.flowers[i], client.request) == 0)
			founded = 1;
	}
	
	return founded;
}

void initArr(Request* clientArr){
	int i;

	for(i = 0; i < REQSIZE; i++){
		clientArr[i].empty = 0;
	}
}

void addIntoList(Request* clientArr, Request client){
	int count;

	for(count = 0; count < REQSIZE; count++){
		if(clientArr[count].empty == 0){
			break;
		}
	}

	strcpy(clientArr[count].name, client.name);
	clientArr[count].coord[0] = client.coord[0];
	clientArr[count].coord[1] = client.coord[1];
	strcpy(clientArr[count].request, client.request);
	clientArr[count].empty = client.empty;
}

void processIt(Request* clientArr){
	int count;
	
	for(count = 0; count < REQSIZE - 1; count++){
		strcpy(clientArr[count].name, clientArr[count+1].name);
		clientArr[count].coord[0] = clientArr[count+1].coord[0];
		clientArr[count].coord[1] = clientArr[count+1].coord[1];
		strcpy(clientArr[count].request, clientArr[count+1].request);
		clientArr[count].empty = clientArr[count+1].empty;
	}
}

double distance(Request r, Florist f){
	return sqrt(pow(r.coord[0] - f.coord[0],2) + pow(r.coord[1] - f.coord[1], 2));
}
