'''
In test_main() function, I created two arrays and called find_kth_book_1 function.

In find_kth_book_1() function, I created two variables that point the ending point
of these two arrays. There are gonna be used in kth_func() function.

In kth_func() function, I find the middle index of both arrays and compared
the middle elements of these two arrays. According to the result, arr1 or arr2
is cut by recursion.

Key also changes if sum of middle indices of these two arrays are smaller than the key.

Source = http://nriverwang.blogspot.com.tr/2013/04/k-th-smallest-element-of-two-sorted.html
'''

def test_main():

    m = ["algotihm", "programminglanguages", "systemsprogramming"]
    n = ["computergraphics", "cprogramming","oop"]

    book = find_kth_book_1(m,n,4)
    print(book)

def find_kth_book_1(arr_1, arr_2, key):

    end_of_m = arr_1[len(arr_1):]
    end_of_n = arr_2[len(arr_2):]

    return kth_func(arr_1, arr_2, end_of_m, end_of_n, key - 1)

def kth_func(arr_1, arr_2, end_of_m, end_of_n,k):

    if(arr_1 == end_of_m):
        return arr_2[k]
    if(arr_2 == end_of_n):
        return arr_1[k]

    arr_1_mid = (len(arr_1) - len(end_of_m)) // 2
    arr_2_mid = (len(arr_2) - len(end_of_n)) // 2

    if(arr_1_mid + arr_2_mid < k):
        if(arr_1[arr_1_mid] > arr_2[arr_2_mid]):
            return kth_func(arr_1, arr_2[arr_2_mid + 1:], end_of_m, end_of_n, k - arr_2_mid - 1)
        else:
            return kth_func(arr_1[arr_1_mid + 1:],arr_2, end_of_m, end_of_n, k - arr_1_mid - 1)
    else:
        if(arr_1[arr_1_mid] > arr_2[arr_2_mid]):
            return kth_func(arr_1, arr_2, arr_1[arr_1_mid:], end_of_m, k)
        else:
            return kth_func(arr_1, arr_2, end_of_m, arr_2[arr_2_mid:], k)


test_main()




