'''
In test_main() function, I called longest_common_postfix with inpStrings.

In longest_common_postfix() function, I returned the divide_and_conquer function's return value.

In divide_and_conquer() function, I divided the given array as left and right and returned the
common_part function's return value.

In common_part() function, I find the same parts of given two strings by starting with the
last letter. When there is a discrepancy, loop stops.

divide_and_conquer ----> O(log(n))
common_part ----> O(n)
Other ones ----> O(1)
Worst case ----> O(nlog(n)) ------- Worst case occurs when all of members of the given array are equal. -------

Source = https://www.tutorialspoint.com/data_structures_algorithms/divide_and_conquer.htm
'''

def test_main():

    inpStrings = ["absorpticity", "circulacity", "electricity", "importunity", "humanity"]

    print(longest_common_postfix(inpStrings))

def longest_common_postfix(inpStrings):

    return divide_and_conquer(inpStrings, 0, len(inpStrings)-1)

def divide_and_conquer(arr,left,right):

    if left < right:

        medium = (left+(right-1))//2

        l = divide_and_conquer(arr, left, medium)
        r = divide_and_conquer(arr, medium+1, right)
        return common_part(l,r)

    return arr[left]

def common_part(str1, str2):

    common = ""

    counter = 0
    index = -1
    limit = min(len(str1), len(str2))

    while(counter < limit):

        if(str1[index] == str2[index]):
            common += str1[index]
        else:
            break

        index -= 1

        counter += 1

    return common[::-1]

test_main()