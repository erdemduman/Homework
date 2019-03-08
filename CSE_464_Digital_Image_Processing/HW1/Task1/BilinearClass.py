import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
import math

class ScaleAndRotate:
    def __init__(self):
        self.__inputName = None
        self.__outputName = None
        self.__inputImg = None
        self.__outputImg = None
        self.__height = 0
        self.__width = 0
        self.__totalPixels = 0

    def scale(self, inputName, width_num, height_num, radian):
        width_num = math.ceil(width_num)
        height_num = math.ceil(height_num) 
        self.__inputName = inputName

        try:
            self.__inputImg = cv.imread(self.__inputName, 0)
        except:
            print("There is no image file like " + str(self.__inputName))

        self.__height, self.__width = self.__inputImg.shape
        self.__totalPixels = self.__height * self.__width 

        self.__makeItWide(width_num, height_num)
        self.__fillBlanks(width_num, height_num)


    def __makeItWide(self, width_num, height_num):
        new_height = int(self.__height * height_num)
        new_width = int(self.__width * width_num)

        self.__outputImg = np.zeros((new_height, new_width))
        
        for h in range(0,self.__height):
            for w in range(0,self.__width):
                self.__outputImg[h*height_num][w*width_num] = self.__inputImg[h][w]
    
    def __fillBlanks(self, width_num, height_num):
        
        for h in range(0, (self.__height-2)*height_num+height_num+1):
            for w in range(0, (self.__width-2)*width_num+width_num+1):
                height_index = 0
                width_index = 0
                if h % height_num == 0 and w % width_num == 0:
                    continue
                else:
                    if(h != 0):
                        height_index = math.ceil(h/height_num)*height_num-height_num
                    if(w != 0):
                        width_index = math.ceil(w/width_num)*width_num-width_num
                    
                    n1 = self.__outputImg[height_index][width_index]
                    n2 = self.__outputImg[height_index + height_num][width_index]
                    n3 = self.__outputImg[height_index][width_index + width_num]
                    n4 = self.__outputImg[height_index + height_num][width_index + width_num]


                    height_dist = h%height_num
                    width_dist = w%width_num

                    d_height = height_dist/height_num
                    d_width = width_dist/width_num

                    c1 = n1
                    c2 = n2-n1
                    c3 = n3-n1
                    c4 = n1-n2-n3+n4

                    self.__outputImg[h][w] = c1+c2*d_height+c3*d_width+c4*d_height*d_width
                    

        for i in range(1, self.__height*height_num-h):
            for j in range(0, self.__width*width_num-(self.__width*width_num-w)+width_num):
                self.__outputImg[h+i][j] = self.__outputImg[h][j]
        
        for i in range(0,self.__height*height_num):
            for j in range(0, self.__width*width_num-w):
                self.__outputImg[i][w+j] = self.__outputImg[i][w]
         
                              
    
    def showImg(self, outputName, save):
        self.__outputName = outputName
        if(save):
            cv.imwrite(self.__outputName, self.__outputImg)
            print("Output image was saved as " + str(self.__outputName))
        
        else:
            print("Output image was not saved.")
        
        plt.imshow(self.__outputImg, "gray")
        plt.show()
