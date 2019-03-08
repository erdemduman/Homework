import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt

class Convolution:
    def __init__(self, kernel, img):
        self.__kernel = kernel.getKernel()
        self.__kernelSize = kernel.getKernelSize()
        self.__img = img
        self.__returnImg = None
        self.__inputHeight = 0
        self.__inputWidth = 0
        self.__fixedHeight = 0
        self.__fixedWidth = 0
    
    def start(self):
        self.__initHeightAndWidth()
        helperImg = np.ones((self.__fixedHeight, self.__fixedWidth))
        self.__initImg(helperImg)
        self.__mirror(helperImg)
        
        self.__returnImg = np.zeros((self.__fixedHeight, self.__fixedWidth))

        for i in range(0, self.__inputHeight):
            for j in range(0, self.__inputWidth):
                self.__returnImg[i][j] = self.__img[i][j]

        
        for i in range(0, self.__inputHeight):
            for j in range(0, self.__inputWidth):
                result = self.__process(helperImg, i,j)
                self.__returnImg[i][j] = result

        return self.__returnImg
    
    def __initHeightAndWidth(self):
        self.__inputHeight, self.__inputWidth = self.__img.shape
        self.__fixedHeight = self.__inputHeight + (int(self.__kernelSize/2)*2)
        self.__fixedWidth = self.__inputWidth + (int(self.__kernelSize/2)*2)
    
    def __initImg(self, helperImg):
        padding = int(self.__kernelSize/2)
        for i in range(0, self.__inputHeight):
            for j in range(0, self.__inputWidth):
                helperImg[i + padding][j + padding] = self.__img[i][j]
    
    def __mirror(self, helperImg):
        padding = int(self.__kernelSize/2)
        for i in range(0, padding):
            for j in range(0, self.__fixedWidth):
                helperImg[i][j] = helperImg[padding][j]
        
        for i in range(self.__inputHeight, self.__fixedHeight):
            for j in range(0, self.__fixedWidth):
                helperImg[i][j] = helperImg[self.__inputHeight-1][j]

        for i in range(0, padding):
            for j in range(0, self.__fixedHeight):
                helperImg[j][i] = helperImg[j][padding]

        for i in range(self.__inputWidth, self.__fixedWidth):
            for j in range(0, self.__fixedHeight):
                helperImg[j][i] = helperImg[j][self.__inputWidth-1]

    
    def __process(self, helperImg, current_h, current_w):
        result = 0

        temp_h = current_h
        temp_w = current_w

        for i in range(0, self.__kernelSize):
            for j in range(0, self.__kernelSize):
                result += self.__kernel[i][j] * helperImg[temp_h][temp_w]
                temp_w += 1
            temp_h += 1
            temp_w = current_w

        return result
        
