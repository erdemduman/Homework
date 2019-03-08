'''
In test_main() function, I created an array named "inpArr" and called min_subarray_finder
with it.

In min_subarray_finder() function, I called min_sub_array_info and returned the subarray
which is wanted by the problem.

In min_sub_array_info() function; I find minimum left sub array, minimum right sub array,
minimum sub array which crosses the mid index in a recursively way and returned the minimum one's
info.
Info : (summation of the sub array, starting index of the sub array, finishing index of the sub array).

In crossSum() function, I find minimum left sub array and minimum right sub array summations and returned
this summation with starting and finishing indices.

crossSum ----> O(n)
min_sub_array_info ----> O(log(n))
Other ones ----> O(1)
Worst Case ----> O(nlog(n))

Source = http://web.cs.ucdavis.edu/~bai/ECS122A/Maxsubarray.pdf
'''

def test_main():

    inpArr = [1, -4, -7, 5, -13, 9, 23, -1]

    print(min_subarray_finder(inpArr))
    print(sum(min_subarray_finder(inpArr)))

def min_subarray_finder(inpArr):

    n = len(inpArr)
    max_sum, start_index, end_index = min_sub_array_info(inpArr, 0, n-1)

    return inpArr[start_index : end_index+1]


def min_sub_array_info(arr, low, high):

    if low == high:
        return arr[low], low, high

    middle = (low + high)//2

    left_half, left_index_low, left_index_high = min_sub_array_info(arr, low, middle)
    right_half, right_index_low, right_index_high = min_sub_array_info(arr, middle+1, high)
    cross, left_index_cross, right_index_cross = crossSum(arr, low, middle)

    sums = [left_half, cross, right_half]
    returnInfo = [(left_half, left_index_low, left_index_high),
                  (cross, left_index_cross, right_index_cross),
                  (right_half, right_index_low, right_index_high)]

    return returnInfo[sums.index(min(sums))]

def crossSum(arr, low, middle):

    low_index = 0
    high_index = 0
    default_sum = 0
    sum_of_left = float("inf")
    leftHalf = arr[low : middle+1]
    leftHalfReversed = leftHalf[::-1]

    for i in leftHalfReversed:
        default_sum += i
        if(default_sum < sum_of_left):
            sum_of_left = default_sum
            low_index = arr.index(i)

    default_sum = 0
    sum_of_right = float("inf")
    rightHalf = arr[middle+1 :]

    for i in rightHalf:
        default_sum += i
        if(default_sum < sum_of_right):
            sum_of_right = default_sum
            high_index = arr.index(i)


    return sum_of_left + sum_of_right, low_index, high_index

test_main()