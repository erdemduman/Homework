def findRottenWalnut(walnutList):
    half = int(len(walnutList)/2)
    half_and_one = int((len(walnutList)/2)+1)
    left = walnutList[:half]
    right = walnutList[half:]
    other_right = walnutList[half_and_one:]

    print(walnutList)

    if len(walnutList)%2 == 1:
        retVal = compareScales(left, other_right)

    else:
        retVal = compareScales(left, right)


    if retVal == 1:
        return findRottenWalnut(left)
    elif retVal == -1:
        return findRottenWalnut(right) + half
    else:
        return half



def compareScales (leftScaleList, rightScaleList):
    result = sum(leftScaleList) - sum(rightScaleList)
    if result < 0:
        return 1
    elif result > 0:
        return -1
    else:
        return 0


print(findRottenWalnut([1,1,0.5,1,1,1]))