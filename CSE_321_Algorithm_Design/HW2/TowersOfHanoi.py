def timer():

    disk = 3
    arr = []

    for i in range(disk):
        arr.append(0)

    TOHtime(disk, "SRC", "AUX", "DST", arr)

    index = 1

    for i in arr:
        print("Elapsed time for disk " + str(index) + ": " + str(i))
        index += 1

def cost(first, second):

    if(first == "SRC" and second == "AUX") or (first == "AUX" and second == "SRC"):
        return 1
    elif (first == "SRC" and second == "DST") or (first == "DST" and second == "SRC"):
        return 2
    elif (first == "DST" and second == "AUX") or (first == "AUX" and second == "DST"):
        return 1



def TOHtime(disk, peg1, peg2, peg3, arr):

    if disk == 1:
        print ("Disk " + str(disk) +  ": " + str(peg1) + " to " + str(peg3))
        arr[disk-1] += disk*cost(peg1, peg3)
        return
    TOHtime(disk-1, peg1, peg3, peg2,arr)
    print ("Disk " + str(disk) +  ": " + str(peg1) + " to " + str(peg3))
    arr[disk-1] += disk*cost(peg1, peg3)

    TOHtime(disk-1, peg2, peg1, peg3,arr)

timer()





