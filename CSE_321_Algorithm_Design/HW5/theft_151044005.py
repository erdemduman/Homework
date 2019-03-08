'''
I will explain this algorithm with an example:

[10,33,13,15]
[22,21,4,1]
[5,0,2,3]
[0,6,14,2]

Start value is 33. (arr[0][1])
33 will be summed with 10 and 22, bigger result is gonna be
written on 33.

The next value is 21. It will be summed with 10, 22 and 5.
The biggest result will be written on 22.

After finishing the first column, the following column will be
processed with the previous column.

And the maximum of the last column will be returned.

Worst case = O(m*n) ----> There is a nested for loop to traverse 2D matrix.
'''

def test_main():

    amountOfMoneyInLand = [[10,33,13,15], [22,21,4,1], [5,0,2,3], [0,6,14,2]]
    res = theft(amountOfMoneyInLand)
    print(res)

def theft(money_list):

    for j in range(1, len(money_list[0])):
        for i in range(0, len(money_list)):
            if(i == 0):
                first_path = money_list[i][j-1] + money_list[i][j]
                second_path = money_list[i+1][j-1] + money_list[i][j]
                max_path = max(first_path, second_path)
                money_list[i][j] = max_path

            elif(i == len(money_list)-1):
                first_path = money_list[i-1][j-1] + money_list[i][j]
                second_path = money_list[i][j-1] + money_list[i][j]
                max_path = max(first_path, second_path)
                money_list[i][j] = max_path

            else:
                first_path = money_list[i-1][j-1] + money_list[i][j]
                second_path = money_list[i][j-1] + money_list[i][j]
                third_path = money_list[i+1][j-1] + money_list[i][j]
                max_path = max(first_path, second_path, third_path)
                money_list[i][j] = max_path

    maximum = 0

    for a in money_list:
        if(a[-1] > maximum):
            maximum = a[-1]

    return maximum


test_main()