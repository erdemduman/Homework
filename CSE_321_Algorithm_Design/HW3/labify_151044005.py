def test_main():

    '''
    mapOfGTU = {
        1 : set([2,3]),
        2 : set([1,3]),
        3 : set([1,2])
    }
    '''


    mapOfGTU = {1 : set([2,3]),
                2 : set([1,3,4]),
                3 : set([1,2,4]),
                4 : set([3,2]),
                5 : set([6]),
                6 : set([5])
    }


    #print(findMinimumCostToLabifyGTU(2,1,mapOfGTU))
    print(findMinimumCostToLabifyGTU(5,2,mapOfGTU))

def findMinimumCostToLabifyGTU(x,y, graph):

    unique = []
    road_number = 0

    if x <= y:
        cost = x*len(graph)
    else:
        unique, connectedGraphs = howManyConnected(graph)
        for i in unique:
            road_number += len(i)-1

        cost = (connectedGraphs*x) + (road_number*y)


    return cost

def howManyConnected(graph):

    roads = []
    unique = []

    for k,v in graph.items():
        roads.append(set(breadth_first_search(k,graph)))

    value = check_unique_number(roads, unique)

    return unique, value

def check_unique_number(road_list, temp):

    for i in road_list:
        if i not in temp:
            temp.append(i)

    return len(temp)

def breadth_first_search(start, graph):

    isVisited = {}

    for k,v in graph.items():
        isVisited[k] = False

    queue = []
    order = []

    isVisited[start] = True
    queue.append(start)

    while(len(queue) != 0):
        start = queue.pop(0)
        order.append(start)

        adj = list(graph[start])

        for i in adj:
            next = i
            if (isVisited[next] != True):
                isVisited[next] = True
                queue.append(next)

    return order


test_main()