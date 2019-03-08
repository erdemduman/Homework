#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/types.h>

void ls(int toFile, char* filename, int isPiped);

int main(int argc, char* argv[]){

    int sizeArr = atoi(argv[1]);
    int isPiped = atoi(argv[2]);
    
    if(sizeArr == 3 && (strcmp(argv[3], ">") == 0)){
        if(argv[4] != NULL){
            ls(1, argv[4], isPiped);
        }
    }

    else{
        ls(0, "gereksiz", isPiped);
    }
    
    return 0;
}

void ls(int toFile, char* filename, int isPiped){
    char str[1024];
    size_t sizeinp = 1024;
    struct dirent *dirStruct;
    DIR* dirptr;
    FILE *fptr;
    size_t filesize;
    struct stat st;

    if(toFile == 1){
        fptr = fopen(filename, "w");
        if(fptr == NULL){
            fprintf(stderr, "No such file or directory like %s\n", filename);
        }
    }

    if(isPiped == 1){
        fptr = fopen("con.txt", "w");
        if(fptr == NULL){
            fprintf(stderr, "No such file or directory like %s\n", filename);
        }
    }

    getcwd(str, sizeinp);
    dirptr = opendir(str);

    if (dirptr == NULL) {
        perror("There is a trouble with path.\n");
    }

    else{
        while ((dirStruct = readdir(dirptr)) != NULL) {
            stat(dirStruct->d_name, &st);
            
            filesize = st.st_size;

            if(toFile == 1 || isPiped){
                fprintf(fptr , (S_ISDIR(st.st_mode)) ? "d" : "-");
                fprintf(fptr , (st.st_mode & S_IRUSR) ? "r" : "-");
                fprintf(fptr , (st.st_mode & S_IWUSR) ? "w" : "-");
                fprintf(fptr , (st.st_mode & S_IXUSR) ? "x" : "-");
                fprintf(fptr , (st.st_mode & S_IRGRP) ? "r" : "-");
                fprintf(fptr , (st.st_mode & S_IWGRP) ? "w" : "-");
                fprintf(fptr , (st.st_mode & S_IXGRP) ? "x" : "-");
                fprintf(fptr , (st.st_mode & S_IROTH) ? "r" : "-");
                fprintf(fptr , (st.st_mode & S_IWOTH) ? "w" : "-");
                fprintf(fptr , (st.st_mode & S_IXOTH) ? "x " : "- ");
                fprintf(fptr,"%lu ", filesize);
                fprintf(fptr,"%s\n", dirStruct->d_name); 
            }

            else{
                fprintf(stdout , (S_ISDIR(st.st_mode)) ? "d" : "-");
                fprintf(stdout , (st.st_mode & S_IRUSR) ? "r" : "-");
                fprintf(stdout , (st.st_mode & S_IWUSR) ? "w" : "-");
                fprintf(stdout , (st.st_mode & S_IXUSR) ? "x" : "-");
                fprintf(stdout , (st.st_mode & S_IRGRP) ? "r" : "-");
                fprintf(stdout , (st.st_mode & S_IWGRP) ? "w" : "-");
                fprintf(stdout , (st.st_mode & S_IXGRP) ? "x" : "-");
                fprintf(stdout , (st.st_mode & S_IROTH) ? "r" : "-");
                fprintf(stdout , (st.st_mode & S_IWOTH) ? "w" : "-");
                fprintf(stdout , (st.st_mode & S_IXOTH) ? "x " : "- ");
                fprintf(stdout,"%lu ", filesize);
                fprintf(stdout,"%s\n", dirStruct->d_name);
            }
            
            
        }

    }

    if(toFile == 1 || isPiped == 1){
        fclose(fptr);
    }

    closedir(dirptr);
}

