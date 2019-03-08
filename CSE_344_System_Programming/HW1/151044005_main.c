/* 151044005 Hakki Erdem Duman */



#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <memory.h>

#include <unistd.h>

/* Prototypes */

void rfile(char* filename, mode_t flags, mode_t filePerms);
char* getByteOrder(unsigned char* buf);
void intel(unsigned char* buf);
void motorola(unsigned char* buf);
int* getWidthAndHeight(unsigned char* buf, int flag);
void draw(unsigned char* buf, int width, int height, int limit);
void drawHelper(unsigned char* buf, int index);


int main(int argc, char* argv[]) {

    mode_t filePerms;
    mode_t readFlags;
    filePerms = S_IWUSR | S_IRUSR | S_IRGRP | S_IWGRP | S_IWOTH | S_IROTH;
    readFlags = O_RDONLY;

    if(argc != 2){
        perror("There must be two arguments. You can use the program by typing:\n./tiffprocessor <filename>\nEnjoy!\n");
    }

    else{
        rfile(argv[1], readFlags, filePerms);
    }


    return 0;
}

/* This method reads the file and calls other helper functions. */

void rfile(char* filename, mode_t flags, mode_t filePerms){

    int fd;
    unsigned char* buf;
    off_t filesize;
    char* byteorder = NULL;
    struct stat st;
    
    /* I found the size of file here. Memory is allocated according to it. */
    stat(filename, &st);
    filesize = st.st_size;

    buf = (unsigned char*)malloc(sizeof(unsigned char)*filesize);

    fd = open(filename, flags, filePerms);

    if(fd == -1){
        perror("There is a trouble with opening file.");
        exit(1);
    }

    read(fd, buf, filesize);

    byteorder = getByteOrder(buf);
    
    if(atoi(byteorder) == 4949){
        intel(buf);
    }

    /* Atoi changes 4d4d to 4 */
    else if(atoi(byteorder) == 4){
        motorola(buf);
    }

    else{
        perror("The file's byte order is neither Intel nor Motorola.\n");
    }
    
    free(byteorder);
    free(buf);
    close(fd);
}

/* This function returns the byte order information. */

char* getByteOrder(unsigned char* buf){
    char* order;
    order = (char*)malloc(sizeof(char)*4);
    sprintf(order,"%.2x%.2x", buf[0], buf[1]);
    return order;
}

/* Processes with Intel will be done here. */

void intel(unsigned char* buf){
    
    int* widthAndHeight = NULL;

    widthAndHeight = getWidthAndHeight(buf, 0);

    printf("Width: %d pixels.\n", widthAndHeight[0]);
    printf("Height: %d pixels.\n", widthAndHeight[1]);
    printf("Byte Order: Intel.\n\n");

    draw(buf, widthAndHeight[0], widthAndHeight[1], widthAndHeight[2]);

    printf("\n");

    free(widthAndHeight);
    
}

/* Processes with Motorola will be done here. */

void motorola(unsigned char* buf){

    int* widthAndHeight = NULL;

    widthAndHeight = getWidthAndHeight(buf, 1);

    printf("Width: %d pixels.\n", widthAndHeight[0]);
    printf("Height: %d pixels.\n", widthAndHeight[1]);
    printf("Byte Order: Motorola.\n\n");
    printf("\n");

    draw(buf, widthAndHeight[0], widthAndHeight[1], widthAndHeight[2]);

    free(widthAndHeight);
}

/* This method returns an integer array that stores width - height information of file and
   the index that the tags start.*/

/* Since the difference of byte order between Motorola and Intel, the "flag" is needed. "flag"
   variable turns "0" whether the byte order is Intel and turns "1" whether the byte order is Motorola.*/

int* getWidthAndHeight(unsigned char* buf, int flag){
    int i;
    int* widthAndHeight;
    char nextAdress[8];
    char tagsHex[4];
    char tagID[4];
    char cwidth[8], cheight[8];
    int width, height;
    int tagStart;
    int tags;

    widthAndHeight = (int*)malloc(sizeof(int)*3);

    /*I store hexadecimal values into char arrays and convert them to integer values.*/

    if(flag == 0){
        sprintf(nextAdress, "%.2x%.2x%.2x%.2x", buf[7], buf[6], buf[5], buf[4]);
        tagStart = strtoul(nextAdress, NULL, 16);
        sprintf(tagsHex, "%.2x%.2x", buf[tagStart+1], buf[tagStart]);
        tags = strtoul(tagsHex, NULL, 16);
        widthAndHeight[2] = tagStart;
        tagStart = tagStart + 2;
        
        for(i = tagStart; i < tagStart + (tags*12); i = i + 12){
            sprintf(tagID, "%.2x%.2x", buf[i+1], buf[i]);
            if(strcmp(tagID, "0100") == 0){
                sprintf(cwidth,"%.2x%.2x%.2x%.2x", buf[i+11], buf[i+10], buf[i+9], buf[i+8]);
                width = strtoul(cwidth, NULL, 16);
                widthAndHeight[0] = width;
            }

            else if(strcmp(tagID, "0101") == 0){
                sprintf(cheight,"%.2x%.2x%.2x%.2x", buf[i+11], buf[i+10], buf[i+9], buf[i+8]);
                height = strtoul(cheight, NULL, 16);
                widthAndHeight[1] = height;
            }
        }
    }

    if(flag == 1){
        sprintf(nextAdress, "%.2x%.2x%.2x%.2x", buf[4], buf[5], buf[6], buf[7]);
        tagStart = strtoul(nextAdress, NULL, 16);
        sprintf(tagsHex, "%.2x%.2x", buf[tagStart], buf[tagStart+1]);
        tags = strtoul(tagsHex, NULL, 16);
        widthAndHeight[2] = tagStart;
        tagStart = tagStart + 2;
        
        for(i = tagStart; i < tagStart + (tags*12); i = i + 12){
            sprintf(tagID, "%.2x%.2x", buf[i], buf[i+1]);
            if(strcmp(tagID, "0100") == 0){
                sprintf(cwidth,"%.2x%.2x%.2x%.2x", buf[i+10], buf[i+11], buf[i+8], buf[i+9]);
                
                width = strtoul(cwidth, NULL, 16);
                widthAndHeight[0] = width;
            }

            else if(strcmp(tagID, "0101") == 0){
                sprintf(cheight,"%.2x%.2x%.2x%.2x", buf[i+10], buf[i+11], buf[i+8], buf[i+9]);
                
                height = strtoul(cheight, NULL, 16);
                widthAndHeight[1] = height;
            }
        }
    }

    return widthAndHeight;

}

/* This function makes the art happen. */

void draw(unsigned char* buf, int width ,int height, int limit){

    int i,j;
    i = 8;
    j = i + width;

    for(i = 8; i < limit; i++){

        if(i < j){
            drawHelper(buf, i);
        }

        else{
            printf("\n");
            drawHelper(buf, i);
            j = j + height;
        }
    }
}

/* Helper of the "draw" function. */

void drawHelper(unsigned char* buf, int index){

    char val[2];
    sprintf(val, "%.2x", buf[index]);
    if(strcmp(val, "ff")){
        printf("0 ");
    }
    else{
        printf("1 ");
    }
}

