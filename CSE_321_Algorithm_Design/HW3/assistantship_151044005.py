def test_main():
    inputTable = [[5,8,7],
                  [8,12,7],
                  [4,8,5]]

    if len(inputTable[0]) > len(inputTable):
        print("Invalid table!")

    else:
        combinations=[]
        permutation(inputTable, 0, len(inputTable),combinations)
        groupedList = group_them(combinations, len(inputTable))

        min = float("inf")
        order = []

        for i in range(0,len(groupedList[0])):
            order.append(0)

        gap = len(inputTable) - len(inputTable[0])
        gap = int(gap)
        for i in groupedList:
            result = 0
            for j in range(0,len(inputTable)-gap):

                result = result + i[j][j]

            result += gap*6

            if result < min:
                min = result
                for k in range(0,len(inputTable)):
                    ind = i.index(inputTable[k])
                    if ind >= len(i) - gap:
                        order[k] = -1
                    else:
                        order[k] = i.index(inputTable[k])

        print(str(min) + "\n" + str(order))


def permutation(table, start, size, all_combinations):

    if start == size-1:
        for i in table:
            all_combinations.append(i)

    else:
        for index in range(start,size):
            temp = table[index]
            table[index] = table[start]
            table[start] = temp

            permutation(table, start+1, size, all_combinations)

            temp = table[index]
            table[index] = table[start]
            table[start] = temp

def group_them(theList, number):
    index = 0
    returnedList = []
    for i in range(0,len(theList)//number):
        temp = []
        for j in range(0,number):
            temp.append(theList[index])
            index += 1
        returnedList.append(temp)

    return returnedList

test_main()

