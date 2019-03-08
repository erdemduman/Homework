import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
import HistogramClass

def main():
    histObj = HistogramClass.Histogram()
    histObj.equalize("../valve.png")
    histObj.showImg("output.png",True) #Pass True whether you want to save image, False otherwise.

 

if __name__ == "__main__":
    main()
