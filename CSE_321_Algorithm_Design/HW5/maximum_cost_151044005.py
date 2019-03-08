'''
This algorithm checks current index, next index and the next index of
the next index to perform some operations.
For example, we assume that our index is i. We are gonna look at the
difference between i - i+1 and i+2 - i+1. That's the normal case.
And then we are gonna change i to 1 and i+1 to 1. This will create other
cases. We are gonna change their values according to result.

Worst case = O(n) ---> There is a loop to traverse list.
'''

def test_main():
    #Y = [14,1,14,1,14]
    #Y = [1,9,11,7,3]
    #Y = [50,28,1,1,13,7]
    #Y = [80,22,45,11,67,67,74,91,4,35,34,65,80,21,95,1,52,25,31,2,53]
    Y = [79,6,40,68,68,16,40,63,93,49,91]
    cost = find_maximum_cost(Y)
    print(cost)

def find_maximum_cost(cost_list):

    last_controllable_index = 2

    for i in range(0,len(cost_list)-1):
        if(i < len(cost_list) - last_controllable_index):
            normal_mode = abs(cost_list[i] - cost_list[i + 1]) + abs(cost_list[i + 2] - cost_list[i + 1])
            changed_mode_1 = abs(cost_list[i + 1] - 1) + abs(cost_list[i + 2] - cost_list[i + 1])
            changed_mode_2 = abs(cost_list[i] - 1) + abs(cost_list[i + 2] - 1)
        else:
            normal_mode = abs(cost_list[i] - cost_list[i + 1])
            changed_mode_1 = abs(1 - cost_list[i + 1])
            changed_mode_2 = abs(1 - cost_list[i])

        if max(changed_mode_1, changed_mode_2, normal_mode) == changed_mode_1:
            cost_list[i] = 1
        if changed_mode_2 > normal_mode and changed_mode_2 >= changed_mode_1:
            cost_list[i+1] = 1

    max_cost = 0

    for i in range(1,len(cost_list)):
        max_cost += abs((cost_list[i] - cost_list[i - 1]))

    return max_cost

test_main()