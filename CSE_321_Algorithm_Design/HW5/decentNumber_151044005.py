'''
In this algorithm, We filled every digit with 5
and according to number of 5s and 3s, we change
the last '5' to '3'. If the number of 5s can be
divided by 3 and number of 3s can be divided by
5, we stop.

I started to fill with 5s because we are wanted to
find the biggest decent number if there is more
than one.

Worst Case = O(n) ---> If we changed every 5 with
'3', that would be the worst case.
'''

def test_main():
    dn = decentNumber(11)
    print(dn)

def decentNumber(digit):
    if(digit < 1):
        return -1

    decent_number = ""
    rule_of_five = 3
    rule_of_three = 5
    number_of_five = digit

    for i in range(0,number_of_five):
        decent_number += '5'

    five_count = number_of_five
    three_count = 0

    while(True):
        if(five_count % rule_of_five == 0 and three_count % rule_of_three == 0):
            break
        else:
            if(five_count == 0):
                return -1
            else:
                decent_number, five_count, three_count = change_number(decent_number, five_count, three_count)

    return decent_number

def change_number(decent_number, five_count, three_count):
    decent_number = list(decent_number)
    decent_number[five_count-1] = '3'
    decent_number = "".join(decent_number)
    five_count -= 1
    three_count += 1

    return decent_number, five_count, three_count

test_main()