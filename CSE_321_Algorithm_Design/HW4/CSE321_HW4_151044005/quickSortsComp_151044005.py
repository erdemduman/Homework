'''
Hoare’s method is more efficient than Lomuto’s method
because it does fewer swap operations, and
it creates efficient partitions even when all values are equal.

Lomuto's method is easier to implement, that's the advantage for it.

They are almost the same except for number of swaps.

source = http://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
'''

def test_main():

    arr = [15,4,68,24,75,16,42]

    qsh = quickSortHoare(arr)

    print(qsh)

    arr = [15,4,68,24,75,16,42]

    qsl = quickSortLomuto(arr)

    print(qsl)

def quickSortHoare(arr):

    quicksort(arr, 0, len(arr)-1, hoare_part)

    return arr

def quickSortLomuto(arr):

    quicksort(arr, 0, len(arr)-1, lomuto_part)

    return arr



def quicksort(arr, low, high, partFunc):

    if low < high:
        partReturn = partFunc(arr,low,high)

        quicksort(arr, low, partReturn-1, partFunc)
        quicksort(arr, partReturn+1, high, partFunc)


def hoare_part(arr, low, high):

     pivot = arr[low]

     a = low
     b = high

     while 1:

        while arr[a] < pivot:

            a += 1

        while arr[b] > pivot:

            b -= 1

        if a >= b:
            return b

        arr[a],arr[b] = arr[b], arr[a]



def lomuto_part(arr, low, high):

    pivot = arr[high]

    a = low - 1

    b = low

    while b < high:

        if arr[b] <= pivot:
            a += 1
            arr[a], arr[b] = arr[b], arr[a]

        b += 1

    arr[a+1], arr[high] = arr[high], arr[a+1]

    return a + 1

test_main()