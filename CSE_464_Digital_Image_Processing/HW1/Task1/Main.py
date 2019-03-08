import BilinearClass

def main():
    obj = BilinearClass.ScaleAndRotate()
    obj.scale("../valve.png", 2, 2, 1.57)
    obj.showImg("output.png", True)
    

if __name__ == "__main__":
    main()
