import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt

class Histogram:
    def __init__(self):
        self.__inputName = None
        self.__outputName = None
        self.__hist_dict = {}
        self.__grayColor = 256
        self.__inputImg = None
        self.__outputImg = None
        self.__height = 0
        self.__width = 0
        self.__totalPixels = 0

    def equalize(self, inputName):
        self.__inputName = inputName
        self.__initDict()
        try:
            self.__inputImg = cv.imread(self.__inputName, 0)
        
        except:
            print("There is no image like " + str(self.__inputName))
        
        self.__height, self.__width = self.__inputImg.shape
        self.__totalPixels = self.__height*self.__width
        self.__calcNumbers()
        self.__pmf()
        self.__cdf()
        self.__l_cdf()
        self.__changeImg()


    def __initDict(self):
        for i in range(0, self.__grayColor):
            self.__hist_dict[i] = 0

    def __calcNumbers(self):
        for h in range(0, self.__height):
            for w in range(0, self.__width):
                self.__hist_dict[self.__inputImg[h][w]] += 1
    
    def __pmf(self):
        for i in range(0,self.__grayColor):
            self.__hist_dict[i] = format(self.__hist_dict[i]/self.__totalPixels, '.7f')
    
    def __cdf(self):
        for i in range(1,self.__grayColor):
            self.__hist_dict[i] = format(float(self.__hist_dict[i]) + float(self.__hist_dict[i-1]), '.7f')

    def __l_cdf(self):
        for i in range(0, self.__grayColor):
            self.__hist_dict[i] = int(round(255*float(self.__hist_dict[i])))

    def __changeImg(self):
        try:
            self.__outputImg = np.zeros((self.__height,self.__width))
        except:
            print("Output image could not been created.")

        for h in range(0,self.__height):
            for w in range(0, self.__width):
                self.__outputImg[h][w] = self.__hist_dict[self.__inputImg[h][w]]
    
    def showImg(self, outputName,save):
        self.__outputName = outputName
        if(save):
            cv.imwrite(self.__outputName, self.__outputImg)
            print("Output image was saved as " + str(self.__outputName))
        else:
            print("Output image was not saved.")
            

        plt.figure(1)
        plt.subplot(211)
        plt.imshow(self.__inputImg, "gray")

        plt.subplot(212)
        plt.imshow(self.__outputImg, "gray")
        plt.show()
