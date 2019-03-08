#include <stdio.h>
#include <math.h>
#include <memory.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/signal.h>
#include <sys/types.h>
#include "time.h"
#include <fcntl.h>
#include <sys/wait.h>

#define PI 3.14159

char* filename;
int fd;
double* fx;
double* arr2;
double* tempArr;
FILE* afile;
FILE* bfile;
pid_t parentpid = 0;

double giveMeDegree(double number);
int stringsize(char* str);
void solveDFT(double* fx, int N, int line, FILE* fptr);
void generateRand(double* fx, int N);
double* createDFT(int N);
int writeIntoFile(int fd, int N, int stacksize, FILE* fptr);
int readFromFile(int fd, int N, int stacksize, FILE* fptr);
void handler();

int main(int argc, char** argv) {

	int i;
	int N, stacksize;

    pid_t childpid;
    struct sigaction obj;
    struct flock filelock;

	mode_t filePerms = S_IWUSR | S_IRUSR | S_IRGRP | S_IWGRP | S_IWOTH | S_IROTH;
	mode_t flags = O_RDWR | O_CREAT;


	if(argc != 7){
		perror("You must run the code like: ./multiprocess_DFT -N 5 -X file.dat -M 100\n");
		exit(1);
	}

	else{
		for(i = 1; i < argc; i++){
			if(strcmp(argv[i], "-N") == 0){
				N = atoi(argv[i+1]);
			}

			else if(strcmp(argv[i], "-X") == 0){
				filename = (char*) malloc(sizeof(char)*(stringsize(argv[i+1])+1));
				strcpy(filename, argv[i+1]);
			}

			else if(strcmp(argv[i], "-M") == 0){
				stacksize = atoi(argv[i+1]);
			}
		}
	}

    obj.sa_handler = &handler;
    obj.sa_flags = 0;

    if(sigemptyset(&obj.sa_mask) == -1){
        perror("Emptying set is failed.\n");
    }

    if(sigaction(SIGINT, &obj, NULL) == -1){
        perror("There is a trouble with SIGINT.\n");
    }

    parentpid = getpid();

	fd = open(filename, flags, filePerms);

	if(fd == -1){
		perror("There is a trouble with opening file.\n");
		exit(1);
	}

    afile = fopen("a.log", "w+");
    bfile = fopen("b.log", "w+");

    if (afile == NULL) {
        perror("Cannot open a file to keep logs of Process A.");
        exit(1);
    }

    if (bfile == NULL) {
        perror("Cannot open a file to keep logs of Process B.");
        exit(1);
    }

    memset(&filelock, 0, sizeof(filelock));

    fx = (double*)malloc(sizeof(double)*N);
    tempArr = (double*)malloc(sizeof(double)*N);
    arr2 = (double*)malloc(sizeof(double)*N);

    if((childpid = fork()) == -1){
        perror("Fork failed, actually.\n");
    }


    for(;;){
        if(childpid == 0){
            filelock.l_type = F_RDLCK;
            fcntl(fd, F_SETLKW, &filelock);
            writeIntoFile(fd, N, stacksize, afile);
            filelock.l_type = F_UNLCK;
            fcntl(fd, F_SETLKW, &filelock);
        }

        if(childpid != 0){
            filelock.l_type = F_WRLCK;
            fcntl(fd, F_SETLKW, &filelock);
            readFromFile(fd, N, stacksize, bfile);
            filelock.l_type = F_UNLCK;
            fcntl(fd, F_SETLKW, &filelock);
        }
    }

	return 0;
}

void handler(){

    free(fx);
    free(tempArr);
    free(arr2);
    close(fd);
    fclose(afile);
    fclose(bfile);
    unlink(filename);
    free(filename);

    if(parentpid == getpid()){
        wait(NULL);
        exit(0);

    } else {
        exit(0);
    }
}

double* createDFT(int N){

	generateRand(fx, N);

	return fx;
}

int stringsize(char* str){

	int size = 0;
	int i;
	for(i = 0; str[i] != '\0'; i++){
		++size;
	}

	return size;
}

double giveMeDegree(double number){
    return (number)*180;
}

void generateRand(double* fx, int N){
	struct timespec times;
	int n;

	clock_gettime(CLOCK_REALTIME,&times);

	srand(times.tv_nsec);

	for(n = 0; n < N; n++){
		fx[n] = ((double)rand()/(double)(RAND_MAX)) * 10.0;
		fx[n] = roundf(fx[n] * 10) / 10;
	}
}

void printFX(double* fx, int N){
	int n;

	for(n = 0; n < N; n++){
		printf("%f ", fx[n]);
	}

	printf("\n");
}

void solveDFT(double* fx, int N, int line, FILE* fptr){

	int n,k;
	double cos_res, sin_res, degree;
	double result;

    printf("Process B: (Line %d): ( ", line);
    fprintf(fptr,"Process B: (Line %d): ( ", line);
    for(int a = 0; a < N; a++){
        printf("%.1f ", tempArr[a]);
        fprintf(fptr,"%.1f ", tempArr[a]);
    }

    printf("): ");


	for(k = 0; k < N; k++){
		cos_res = 0.0;
		sin_res = 0.0;
		for(n = 0; n < N; n++){

			result = (double)(-(2*n*k))/(double)N;
			degree = giveMeDegree(result);

			cos_res += fx[n]*cos(degree*(PI/180));
			sin_res += fx[n]*sin(degree*(PI/180));
		}
        if(sin_res >= 0){
            printf("%.3f+%.3fi ", cos_res, sin_res);
            fprintf(fptr,"%.3f+%.3fi ", cos_res, sin_res);
        }
        else{
            printf("%.3f%.3fi ", cos_res, sin_res);
            fprintf(fptr,"%.3f%.3fi ", cos_res, sin_res);

        }

	}
}

int writeIntoFile(int fd, int N, int stacksize, FILE* fptr){

    double* arr;
    char c;
    double temp;
    int line = 0;

    c = '\n';

    arr = createDFT(N);

    printf("Process A: i’m producing a random sequence: ");
    fprintf(fptr ,"Process A: i’m producing a random sequence: ");

    for(int i = 0; i < N; i++){
        printf("%.1f ", arr[i]);
        fprintf(fptr ,"%.1f ", arr[i]);
        write(fd, &arr[i], sizeof(double));
    }

    printf("\n");
    fprintf(fptr,"\n");

    write(fd, &c, sizeof(char));

}

int readFromFile(int fd, int N, int stacksize, FILE* fptr){

    int a, i;
    int line;
    int templine;
    double temp;
    char c;

    lseek(fd, 0, SEEK_SET);

    line = 0;

    while(read(fd, &temp, sizeof(double))) {
        for(int i = 0; i < N-1; i++){
            read(fd, &temp, sizeof(double));
        }

        read(fd, &c, sizeof(char));

        if (c == '\n') {
            line++;
        }
    }

    if(line == 0)
        return -1;

    lseek(fd, -(N*sizeof(double) + sizeof(char)), SEEK_END);


    for(a = 0; a < N; a++){
        read(fd, &tempArr[a], sizeof(double));
    }

    read(fd, &c, sizeof(char));

    printf("\n");
    fprintf(fptr,"\n");

    ftruncate(fd, (sizeof(double)*N+1)*(line-1));
    line--;

    lseek(fd, 0, SEEK_SET);

    for(a = 0; a < line; a++){
        for(i = 0; i < N; i++){
            read(fd, &arr2[i], sizeof(double));
        }
        read(fd, &c, sizeof(char));
    }


    templine = line + 1;

    solveDFT(tempArr, N, templine, fptr);

    return 0;
}
