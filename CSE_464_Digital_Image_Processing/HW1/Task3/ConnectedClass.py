import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
import sys

class ConnectedComponents:
    def __init__(self, inputName, outputName):
        self.__height = 0
        self.__width = 0
        self.__inputName = inputName
        self.__outputName = outputName
        self.__inputImg = None
        self.__outputImg = None

    def denoise(self):
        self.__inputImg = cv.imread(self.__inputName,0)
        self.__height, self.__width = self.__inputImg.shape
        
        kernel = np.ones((5,5),np.uint8)
        erosion = cv.erode(self.__inputImg,kernel,iterations = 1)
        self.__outputImg = cv.dilate(erosion,kernel,iterations = 1)
    
    def connectedTest(self):
        num, img = cv.connectedComponents(self.__outputImg, connectivity=4)
        print(num)

    def connected(self):
        
        point = 1
        equ_dict = {}
        helper_arr = self.__emtpyArr()
        for h in range(0, self.__height):
            for w in range(0, self.__width):
                if(self.__outputImg[h][w] != 0):
                    neighbor = []
                    if(h == 0 and w == 0):
                        neighbor = []
                    elif(h == 0 and w != 0):
                        neighbor = [(h,w-1)]
                    elif(h != 0 and w == 0):
                        neighbor = [(h-1,w)]
                    else:
                        neighbor = [(h-1,w),(h,w-1)]
                
                    n_value = []
                    
                    for n in neighbor:
                        n_value.append(helper_arr[n[0]][n[1]])
                    
                    if(self.__isAllZero(n_value)):
                        
                        equ_dict[point] = point
                        helper_arr[h][w] = point
                        point += 1
                    
                    elif(self.__isThereZero(n_value)):
                        
                        helper_arr[h][w] = max(n_value)
                    
                    else:
                        if(max(n_value) != min(n_value)):
                            equ_dict[max(n_value)] = min(n_value)
                        helper_arr[h][w] = min(n_value)
        
        count = 0
        for k,v in equ_dict.items():
            if(k == v):
                count += 1  
        
        print("Here is connected components: " + str(count))

        cv.imwrite(self.__outputName, self.__outputImg)
        plt.imshow(self.__outputImg, "gray")
        plt.show()
    
    def __emtpyArr(self):
        arr = []
        for h in range(0, self.__height):
            inner_arr = []
            for w in range(0, self.__width):
                inner_arr.append(0)
            
            arr.append(inner_arr)

        return arr
    
    def __isAllZero(self, arr):
        for i in arr:
            if(i != 0):
                return False
        
        return True
    
    def __isThereZero(self, arr):
        for i in arr:
            if(i == 0):
                return True
        
        return False
