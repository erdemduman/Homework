'''
In test_main() function, I created two arrays and called find_kth_book_2 function.

In find_kth_book_2() function, I decreased key by the number of arr1_index or
arr2_index and when the key occurs 1, I returned the small one of 0th elements of
two arrays.

Source = https://algorithmsandme.in/2014/12/07/find-kth-smallest-element-in-two-sorted-arrays/
'''

def test_main():
    m = ["algotihm", "programminglanguages", "systemsprogramming"]
    n = ["computergraphics", "cprogramming","oop"]

    print(find_kth_book_2(m,n,4))


def find_kth_book_2(arr1, arr2, key):

    if(len(arr1) == 0):
        return arr2[key-1]

    if(len(arr2) == 0):
        return arr1[key-1]

    if(key == 1):
        if(arr1[0] < arr2[0]):
            return arr1[0]
        else:
            return arr2[0]

    temp_key = key // 2

    if(len(arr1) < temp_key):
        arr1_index = len(arr1)
    else:
        arr1_index = temp_key

    if(len(arr2) < temp_key):
        arr2_index = len(arr2)
    else:
        arr2_index = temp_key


    if(arr1[arr1_index-1] < arr2[arr2_index-1]):
        return find_kth_book_2(arr1[arr1_index:], arr2, key - arr1_index)
    else:
        return find_kth_book_2(arr1, arr2[arr2_index:], key - arr2_index)

test_main()