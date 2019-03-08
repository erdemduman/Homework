import cv2 as cv
import numpy as np
import JpegCompression
import math

if __name__ == "__main__":

    img_path = "Images/raven.jpg"
    img = cv.imread(img_path)

    comp = JpegCompression.JpegCompression(img)
    comp.colorSpaceConversion()
    comp.preprocess()
    comp.dctAndQuantization()
    comp.inverseDctAndQ()
    comp.postprocess()
    comp.meanSquareError()
    comp.printImg()



        







