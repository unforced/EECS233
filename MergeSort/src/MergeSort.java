/**
 * A class containing a non-recursive implementation of mergeSort.
 * @author Aaron Neyer
 */
import java.util.Arrays;
import java.util.Stack;
public class MergeSort<AnyType extends Comparable<? super AnyType>> {	
	/**
	* Non recursive mergeSort algorithm.
	* @param a an array of Comparable items.
	*/
	public static <AnyType extends Comparable<? super AnyType>>
	void mergeSort( AnyType [ ] a )
	{
		if (a.length!=0) {
			int left = 0;  //Stores the left side of the section of the array.
			int right = a.length-1;  //Stores the right side of the section of the array.
			int center;  //Stores the center of the section of the array.
			boolean finished=false; //Stays true while there are still cells left to use.
			Stack<Integer[]> mergeStack = new Stack<Integer[]>();  //Stores the values to call merge on.
			Stack<Integer[]> backStack = new Stack<Integer[]>();  //Stores the larger arrays to return to after checking one direction.
			//Iterates through different parts of the array, until it reaches the last one, pushing coordinates to the mergeStack.
			while (!finished) {
				if (right>left) {
					center = (left+right)/2;
					mergeStack.push(new Integer[]{left,center+1,right});
					if (center>left) 
						backStack.push(new Integer[]{left,center});
					if (right==1)
						finished=true;
					left=center+1;
				} else {
					Integer[] coords = backStack.pop();
					left = coords[0];
					right = coords[1];
				}
			}
			AnyType[] tmpArray = (AnyType[]) new Comparable[a.length]; //tmpArray used within merge to place the merged result.
			//Iterates through the mergeStack, calling merge on all of the given coordinates.
			while(!mergeStack.empty()) {
				Integer[] coords = mergeStack.pop(); //Stores the coordinates to call merge on.
				merge(a, tmpArray, coords[0], coords[1], coords[2]);
			}
		}
		 
	}
	
	/**
	* Internal method that merges two sorted halves of a subarray.
	* This is the implementation of merge found in section 7.6 of the book.
	* It is able to be reused because it doesn't use any recursion.
	* @param a an array of Comparable items.
	* @param tmpArray an array to place the merged result.
	* @param leftPos the left-most index of the subarray.
	* @param rightPos the index of the start of the second half.
	* @param rightEnd the right-most index of the subarray.
	*/
	private static <AnyType extends Comparable<? super AnyType>>
	void merge( AnyType [ ] a, AnyType [ ] tmpArray,
			int leftPos, int rightPos, int rightEnd )
	{
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd )
			if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
				tmpArray[ tmpPos++ ] = a[ leftPos++ ];
			else
				tmpArray[ tmpPos++ ] = a[ rightPos++ ];
		while( leftPos <= leftEnd ) // Copy rest of first half
			tmpArray[ tmpPos++ ] = a[ leftPos++ ];
		while( rightPos <= rightEnd ) // Copy rest of right half
			tmpArray[ tmpPos++ ] = a[ rightPos++ ];
		// Copy tmpArray back
		for( int i = 0; i < numElements; i++, rightEnd-- )
			a[ rightEnd ] = tmpArray[ rightEnd ];
	}
	
	public static void main(String[] args) {
		mergeSort(args);
		System.out.println(Arrays.toString(args));
	}
	
}