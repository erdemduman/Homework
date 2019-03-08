import KernelClass
import ConvolutionClass
import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt

def main():
    kernel = KernelClass.Kernel()
    kernel.generateKernel()

    print("************** IMAGE PATH **************\n")
    imgPath = input("Enter the image path: ")
    print("Processing...")
    img = cv.imread(imgPath, 0)

    conv = ConvolutionClass.Convolution(kernel, img)
    new_image = conv.start()

    cv.imwrite("out.png", new_image)
    print("Output image was saved as 'out.png'")

if __name__ == "__main__":
    main()