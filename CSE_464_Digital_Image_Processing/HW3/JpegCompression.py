import cv2 as cv
import numpy as np
import math

class JpegCompression:

    def __init__(self, img):
        self.__img = img
        self.__newImg = None
        self.__dAndQMatrix = None
        self.__blockSize = 8
        self.__finalMatrix = None
        self.__quant_matrix = [[16,11,10,16,24,40,51,61],
                            [12,12,14,19,26,58,60,55],
                            [14,13,16,24,40,57,69,56],
                            [14,17,22,29,51,87,80,62],
                            [18,22,37,56,68,109,103,77],
                            [24,35,55,64,81,104,113,92],
                            [49,64,78,87,103,121,120,101],
                            [72,92,95,98,112,100,103,99]] 

    def colorSpaceConversion(self):
        self.__img = cv.cvtColor(self.__img, cv.COLOR_BGR2YCrCb)

    def preprocess(self):

        self.__padding()
        height, width, depth = self.__newImg.shape
        self.__newImg = np.array(self.__newImg, dtype="int32")

        for h in range(height):
            for w in range(width):
                self.__newImg[h][w][0] -= 128
                self.__newImg[h][w][1] -= 128
                self.__newImg[h][w][2] -= 128
        

    def __padding(self):
        height, width, depth = self.__img.shape
        height_new = height + (8 - (height % 8)) 
        width_new = width + (8 - (width % 8)) 
        
        self.__newImg = np.zeros((height_new, width_new, depth))

        for h in range(0,height):
            for w in range(0,width):
                self.__newImg[h][w][0] = self.__img[h][w][0]
                self.__newImg[h][w][1] = self.__img[h][w][1]
                self.__newImg[h][w][2] = self.__img[h][w][2]

        for h in range(height, height_new):
            for w in range(width):
                self.__newImg[h][w][0] = self.__img[-1][w][0]
                self.__newImg[h][w][1] = self.__img[-1][w][1]
                self.__newImg[h][w][2] = self.__img[-1][w][2]            

        for h in range(height_new):
            for w in range(width, width_new):
                self.__newImg[h][w][0] = self.__newImg[h][height-1][0]
                self.__newImg[h][w][1] = self.__newImg[h][height-1][1]
                self.__newImg[h][w][2] = self.__newImg[h][height-1][2]
                
    def dctAndQuantization(self):
        
        print("DCT")
        self.__dAndQMatrix = np.array(self.__newImg, dtype="int32")

        height, width, depth = self.__newImg.shape
        iter_h = int(height/self.__blockSize)
        iter_w = int(width/self.__blockSize)

        for h in range(0, iter_h):
            for w in range(0, iter_w):
                self.__dAndQHelper(h, w)

    
    def __dAndQHelper(self, cur_h, cur_w):
        
        size = self.__blockSize*self.__blockSize

        for h in range(self.__blockSize):
            for w in range(self.__blockSize):
                
                result0 = 0
                result1 = 0
                result2 = 0

                if (h == 0): 
                    ci = 1 / math.sqrt(self.__blockSize); 
                else:
                    ci = math.sqrt(2) / math.sqrt(self.__blockSize); 
                      
                if (w == 0): 
                    cj = 1 / math.sqrt(self.__blockSize); 
                else:
                    cj = math.sqrt(2) / math.sqrt(self.__blockSize)

                for in_h in range(self.__blockSize):
                    for in_w in range(self.__blockSize):
                
                        result0 += self.__newImg[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][0] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))
                        result1 += self.__newImg[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][1] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))
                        result2 += self.__newImg[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][2] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))
                
                result0 = round(ci * cj * result0)
                result1 = round(ci * cj * result1)
                result2 = round(ci * cj * result2)

                result0 = round(result0/self.__quant_matrix[h][w])
                result1 = round(result1/self.__quant_matrix[h][w])
                result2 = round(result2/self.__quant_matrix[h][w])

                self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][0] = result0
                self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][1] = result1
                self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][2] = result2
                

    def inverseDctAndQ(self):
        print("I-DCT")
        self.__finalMatrix = self.__dAndQMatrix
        height, width, depth = self.__finalMatrix.shape
        iter_h = int(height/self.__blockSize)
        iter_w = int(width/self.__blockSize)

        for h in range(0, iter_h):
            for w in range(0, iter_w):
                self.__inverseDctAndQHelper(h, w)
    
    def __inverseDctAndQHelper(self,cur_h, cur_w):
    
        size = self.__blockSize*self.__blockSize

        for h in range(self.__blockSize):
            for w in range(self.__blockSize):
                result0 = 0
                result1 = 0
                result2 = 0

                if (h == 0): 
                    ci = 1 / math.sqrt(self.__blockSize); 
                else:
                    ci = math.sqrt(2) / math.sqrt(self.__blockSize); 
                      
                if (w == 0): 
                    cj = 1 / math.sqrt(self.__blockSize); 
                else:
                    cj = math.sqrt(2) / math.sqrt(self.__blockSize)
                
                result0 = self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][0]*self.__quant_matrix[h][w]
                result1 = self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][1]*self.__quant_matrix[h][w]
                result2 = self.__dAndQMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][2]*self.__quant_matrix[h][w]

                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][0] = result0
                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][1] = result1
                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][2] = result2

                                
                for in_h in range(self.__blockSize):
                    for in_w in range(self.__blockSize):
                        
                        result0 += ci*cj*self.__finalMatrix[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][0] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))
                        result1 += ci*cj*self.__finalMatrix[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][1] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))
                        result2 += ci*cj*self.__finalMatrix[self.__blockSize*cur_h + in_h][self.__blockSize*cur_w + in_w][2] *  math.cos((math.pi * (in_h+0.5) * h) / (2 * self.__blockSize)) *  math.cos((math.pi * (in_w+0.5) * w) / (2 * self.__blockSize))

                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][0] = round(result0)
                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][1] = round(result1)
                self.__finalMatrix[self.__blockSize*cur_h + h][self.__blockSize*cur_w + w][2] = round(result2)
                                
    def postprocess(self):
        new_h, new_w, new_d = self.__img.shape
        self.__finalMatrix = self.__finalMatrix[0:new_h, 0:new_w]
        
        for h in range(new_h):
            for w in range(new_w):
                self.__finalMatrix[h][w][0] += 128
                self.__finalMatrix[h][w][1] += 128
                self.__finalMatrix[h][w][2] += 128
        
    
        
    def meanSquareError(self):
        height, width, depth = self.__finalMatrix.shape
        size = height*width
        
        result0 = 0
        result1 = 0
        result2 = 0

        for h in range(height):
            for w in range(width):
                result0 += math.pow(self.__img[h][w][0] - self.__finalMatrix[h][w][0], 2)
                result1 += math.pow(self.__img[h][w][1] - self.__finalMatrix[h][w][1], 2)
                result2 += math.pow(self.__img[h][w][2] - self.__finalMatrix[h][w][2], 2)
        
        result0 = result0/size
        result1 = result1/size
        result2 = result2/size

        result = (result0 + result1 + result2) / 3

        print("MSE: " + str(result))

    def printImg(self):
        cv.imwrite("out.jpg", self.__finalMatrix)
        print("Output image is saved as out.jpg")
