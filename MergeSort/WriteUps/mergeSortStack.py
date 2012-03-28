def printMergeStack(left, right, full):
    if right > left:
        center = (left+right)/2
        if full: print "mergeSort({}, {})".format(left, center)
        printMergeStack(left, center, full)
        if full: print "mergeSort({}, {})".format(center+1, right)
        printMergeStack(center+1, right, full)
        print "Merge({}, {}, {})".format(left, center+1, right)

if __name__=="__main__":
    print "Length of 8"
    printMergeStack(0,7,True)
    print "\n\n\nLength of 8 without printing mergeSorts"
    printMergeStack(0,7,False)
