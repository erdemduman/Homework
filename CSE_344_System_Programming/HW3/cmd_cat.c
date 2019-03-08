#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void cat(char* filename);
void catPipe();
void pipeAndFile(char* filename);
void readAndWrite(char* from, char* to);
int isThere(char* buf, char* args[], int argvCount, int arrSize);

int main(int argc, char* argv[]){
    
    int arrSize = atoi(argv[1]);
    int counter;
    int argvCount = 2;
    int isThereCheck;

    arrSize = arrSize - 1;

    if(argv[argvCount] == NULL){
        catPipe();
    }

    else if(strcmp(argv[argvCount], "<") == 0){
        if(argv[argvCount+1] != NULL){
            cat(argv[argvCount+1]);
        }
    }

    else if(strcmp(argv[argvCount], ">") == 0){
        if(argv[argvCount+1] != NULL){
            pipeAndFile(argv[argvCount+1]);
        }
    }

    else{
        isThereCheck = isThere(">", argv, argvCount, arrSize);
        if(isThereCheck != -1 && argv[isThereCheck+1] != NULL){
            for(counter = 0; counter < isThereCheck; counter++){
                readAndWrite(argv[argvCount], argv[isThereCheck+3]);
                argvCount++;   
            }
        }

        else{
            for(counter = 0; counter < arrSize; counter++){
                cat(argv[argvCount]);
                argvCount++;   
            }
        }
        
    }

    

    return 0;
}

void cat(char* filename){

    FILE* fptr;
    char c;
    fptr = fopen(filename, "r");
    

    if(fptr == NULL){
        fprintf(stderr, "No such file or directory like %s\n", filename);
    }
    
    fseek(fptr, 0, SEEK_SET);
    while ((c = fgetc(fptr)) != EOF)
    {
        
        fprintf(stdout,"%c", c);
    }

    fclose(fptr);
}

int isThere(char* buf, char* args[], int argvCount, int arrSize){
    int counter;
    for(counter = 0; counter < arrSize; counter++){
        if(strcmp(args[argvCount], buf) == 0){
            return counter;
        }
        argvCount++;
    }

    return -1;
}

void catPipe(){
    FILE* fptr;
    char c;
    fptr = fopen("con.txt", "r");

    fseek(fptr, 0, SEEK_SET);
    while ((c = fgetc(fptr)) != EOF)
    {
        
        fprintf(stdout,"%c", c);
    }

    fclose(fptr);
    unlink("con.txt");
}

void pipeAndFile(char* filename){
    FILE *fptr_r, *fptr_w;
    char c;
    fptr_r = fopen("con.txt", "r");
    fptr_w = fopen(filename, "w");

    fseek(fptr_r, 0, SEEK_SET);
    while ((c = fgetc(fptr_r)) != EOF)
    {
        fprintf(fptr_w,"%c", c);
    }

    fclose(fptr_r);
    fclose(fptr_w);
    unlink("con.txt");
}

void readAndWrite(char* from, char* to){
    FILE *fptr_r, *fptr_w;
    char c;
    fptr_r = fopen(from, "r");
    fptr_w = fopen(to, "a");
    

    if(fptr_r == NULL){
        fprintf(stderr, "No such file or directory like %s\n", from);
    }

    if(fptr_w == NULL){
        fprintf(stderr, "No such file or directory like %s\n", to);
    }
    
    fseek(fptr_r, 0, SEEK_SET);
    while ((c = fgetc(fptr_r)) != EOF)
    {
        
        fprintf(fptr_w,"%c", c);
    }

    
    fclose(fptr_r);
    fclose(fptr_w);
}