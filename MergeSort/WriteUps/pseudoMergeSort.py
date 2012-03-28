def mergeSort(size):
    left=0
    right=size-1
    center=0
    finished=False
    mergeStack = []
    backStack = []
    while(not finished):
        if right>left:
            center=((right+left)/2)
            mergeStack.append([left, center+1, right])
            if center>left: backStack.append([left, center+1, right])
            if right==1: finished=True
            left=center+1
        else:
            left, center, right = backStack.pop()
            right = center-1
    for x in range(len(mergeStack)):
        vars = mergeStack.pop()
        print "merge({}, {}, {})".format(vars[0], vars[1], vars[2]) 
            
if __name__=="__main__":
    mergeSort(8)

