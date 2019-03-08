#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

#define COMMANDSIZE 512
#define DEFSIZE 64

void menu();
void pwd(char* commands[], char* sizeArr, char* isPiped);
void ls(char* files[], char* arrSize, char* isPiped);
void help(char* commands[], char* sizeArr, char* isPiped);
void cat(char* files[], char* arrSize);
void wc(char* files[], char* arrSize);
void cd(char* commands[], char* sizeArr);
void writeToFile(char* filename, char* buf);
void exit_func();
void commandHandler(char* commands[], char* arrSize, char* isPiped);
void sigtermCatch();
void history(char* commands[], char* sizeArr);

FILE *allptr;
int commandNumber = 0;

int main(int argc, char* argv[]){

    menu();

    return 0;
}

void menu(){
    
    char command[COMMANDSIZE];
    char *pipedOnes[64];
    char *deleteSpaces[64];
    char arrSize[8];
    char* token;
    int pipedOnes_size, deleteSpaces_size, counter1, counter2;
    struct sigaction action;
    
    memset(&action, 0, sizeof(action));
    action.sa_handler = sigtermCatch;
    if(sigaction(SIGTERM, &action, NULL) == -1){
        perror("Error.\n");
    }
 
    while(1){
        printf("$ ");
        fgets(command,COMMANDSIZE, stdin);

         if (command[strlen(command) - 1] == '\n')
            command[strlen(command) - 1] = '\0';

        allptr = fopen("commands.txt", "a");
        fprintf(allptr,"%s\n", command);
        commandNumber++;
        fclose(allptr);

        token = strtok(command, "|");
        for(pipedOnes_size = 0; token != NULL ; pipedOnes_size++){
            pipedOnes[pipedOnes_size] = token;
            
            token = strtok(NULL, "|");
        }

        for(counter1 = 0; counter1 < pipedOnes_size; counter1++){
            strcpy(command, pipedOnes[counter1]);
            
            token = strtok(command, " ");
            for(deleteSpaces_size = 0; token != NULL ; deleteSpaces_size++){
                deleteSpaces[deleteSpaces_size] = token;
            
                token = strtok(NULL, " ");
            }
            
            sprintf(arrSize,"%d", deleteSpaces_size);
            if(pipedOnes_size == 1){
                commandHandler(deleteSpaces, arrSize, "0");
            }

            else{
                commandHandler(deleteSpaces, arrSize, "1");
            }
            
            
        }
        
    }
}

void commandHandler(char* commands[], char* arrSize, char* isPiped){
    
    if(atoi(arrSize) != 0){
        
        if(strcmp(commands[0], "pwd") == 0){
            pwd(commands, arrSize, isPiped);
        }

        else if(strcmp(commands[0], "help") == 0){
            help(commands, arrSize, isPiped);
        }

        else if(strcmp(commands[0], "ls") == 0){
            ls(commands, arrSize, isPiped);
        }

        else if(strcmp(commands[0], "cat") == 0){
            cat(commands, arrSize);
        }

        else if(strcmp(commands[0], "wc") == 0){
            wc(commands, arrSize);
        }

        else if(strcmp(commands[0], "cd") == 0){
            cd(commands, arrSize);
        }

        else if(strcmp(commands[0], "history") == 0){
            history(commands, arrSize);
        }

        else if(strcmp(commands[0], "exit") == 0){
            exit_func();
        }

        else{
            perror("Wrong command.\n");
        }

    }
}

void pwd(char* commands[], char* sizeArr, char* isPiped){
    char str[1024];

    if(atoi(sizeArr) == 3 && (strcmp(commands[1], ">") == 0)){
        if (getcwd(str, sizeof(str)) != NULL){
            writeToFile(commands[2], str);
        }

        else
            perror("Error\n");
    }

    else{
        if (getcwd(str, sizeof(str)) != NULL){
            if(atoi(isPiped) == 1){
                writeToFile("con.txt", str);
            }
            else{
                printf("%s\n", str);
            }
            
            
        }
       
        else
            perror("Error\n");
    }


}

void help(char* commands[], char* sizeArr, char* isPiped){
    char str[96] = "Here are the supported commands:\nls\npwd\ncd\ncat\nwc\nexit\n";

    if(atoi(sizeArr) == 3 && (strcmp(commands[1], ">") == 0)){
        writeToFile(commands[2], str);
    }

    else{
        if(atoi(isPiped) == 1){
            writeToFile("con.txt", str);
        }
        else{
            printf("Here are the supported commands:\n");
            printf("ls\npwd\ncd\ncat\nwc\nexit\n");
        }
        
    }
    
}

void cd(char* commands[], char* sizeArr){
    
    if(atoi(sizeArr) == 2){
        char totalPath[1024] = "./";
        strcat(totalPath, commands[1]);
        if(chdir(totalPath) == -1){
            perror("No such file or directory.\n");
        }
    }
    
}

void writeToFile(char* filename, char* buf){
    FILE* fptr;
    
    fptr = fopen(filename, "w+");

    fprintf(fptr, "%s\n", buf);

    fclose(fptr);
}

void cat(char* files[], char* arrSize){
    int counter;
    int index = 2;
    char *args[DEFSIZE];
    pid_t childpid;

    childpid = fork();

    if(childpid < 0){
        perror("Fork failed.\n");
    }

    else if(childpid == 0){
       
        args[0] = "./cmdcat";
        args[1] = arrSize;
        for(counter = 1; counter < atoi(arrSize); counter++){
            args[index] = files[counter];
            ++index;
        }
        
        args[index] = NULL;
        
        execvp("./cmdcat", args);   

        exit(0);
    }

    else{
        wait(NULL);
    }    
}

void wc(char* files[], char* arrSize){
    int counter;
    int index = 2;
    char *args[DEFSIZE];
    pid_t childpid;

    childpid = fork();

    if(childpid < 0){
        perror("Fork failed.\n");
    }

    else if(childpid == 0){
       
        args[0] = "./cmdwc";
        args[1] = arrSize;
        for(counter = 1; counter < atoi(arrSize); counter++){
            args[index] = files[counter];
            ++index;
        }
        
        args[index] = NULL;
        
        execvp("./cmdwc", args);   

        exit(0);
    }

    else{
        wait(NULL);
    }    
}

void ls(char* files[], char* arrSize, char* isPiped){
    int counter;
    int index = 3;
    char *args[DEFSIZE];
    pid_t childpid;

    childpid = fork();

    if(childpid < 0){
        perror("Fork failed.\n");
    }

    else if(childpid == 0){
       
        args[0] = "./cmdls";
        args[1] = arrSize;
        args[2] = isPiped;
        for(counter = 1; counter < atoi(arrSize); counter++){
            args[index] = files[counter];
            ++index;
        }
        
        args[index] = NULL;
        
        execvp("./cmdls", args);   

        exit(0);
    }

    else{
        wait(NULL);
    }    
}

void sigtermCatch(){
    fprintf(stdout, "Good bye!\n");
    unlink("commands.txt");
    exit(0);
}

void history(char* commands[], char* sizeArr){
    int counter;
    char c;
    int lineCount = 0;
    char line[256];

    allptr = fopen("commands.txt", "r");
    fseek(allptr, 0, SEEK_SET);

    if(atoi(sizeArr) == 1){
        
        while ((c = fgetc(allptr)) != EOF)
        {
            fprintf(stdout,"%c", c);
        }
        
    }

    else if(atoi(sizeArr) == 2){
        if(atoi(commands[1]) > 0){
            while(fgets(line, sizeof(line), allptr)){
                lineCount++;
                if(lineCount == commandNumber - atoi(commands[1])){
                    printf("%s", line);
                }
            }
        }
    }

    fclose(allptr);
    
}

void exit_func(){
    unlink("commands.txt");
    exit(0);
}

