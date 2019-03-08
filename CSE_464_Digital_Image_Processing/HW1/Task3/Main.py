import ConnectedClass

def main():
    obj = ConnectedClass.ConnectedComponents("../abdomen.png", "output.png")
    obj.denoise()
    obj.connected()

if __name__ == "__main__":
    main()