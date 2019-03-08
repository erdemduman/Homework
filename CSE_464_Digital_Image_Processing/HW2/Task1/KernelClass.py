import sys

class Kernel:
    def __init__(self):
        self.__kernelSize = 0
        self.__kernel = []
    
    def generateKernel(self):
        filename = input("Enter the file of kernel: ")
        f = open(filename, 'r')
    
        for line in f:
            line = line.replace("\n", "")
            temp = line.split(",")
            temp = [float(x) for x in temp]
            self.__kernel.append(temp)
        
        self.__kernelSize = len(self.__kernel)
        
        if(self.__kernelSize % 2 == 0):
            print("Kernel is wrong.")
            sys.exit()

        
        for i in self.__kernel:
            if(len(i) != self.__kernelSize):
                print("Kernel is wrong.")
                sys.exit()
        
        self.__flipKernel()
        
        print("Here is your " + str(self.__kernelSize) + "x" + str(self.__kernelSize) + " kernel:\n")

        self.__printKernel()
        
    def __printKernel(self):
        for i in range(0, self.__kernelSize):
            for j in range(0, self.__kernelSize):
                print(self.__kernel[i][j], end = " ")
            
            print("\n")
    
    def getKernel(self):
        return self.__kernel
    
    def getKernelSize(self):
        return self.__kernelSize
    
    def __flipKernel(self):
        for h in range(0, int(self.__kernelSize/2)+1):
            for w in range(0, self.__kernelSize):
                if(h == int(self.__kernelSize/2) and w == int(self.__kernelSize/2)):
                    break
                h_rev = (h+1)*-1
                w_rev = (w+1)*-1
                temp = self.__kernel[h][w]
                self.__kernel[h][w] = self.__kernel[h_rev][w_rev]
                self.__kernel[h_rev][w_rev] = temp

