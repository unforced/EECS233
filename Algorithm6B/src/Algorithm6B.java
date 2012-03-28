import java.util.*;
/**
 * Selects the kth largest element from a LinkedList L using Algorithm 6B from the book.
 * @author Aaron Neyer
 */
public class Algorithm6B {
	/**
	 * Selects the kth largest element from L
	 * @param L LinkedList to select from
	 * @param k Select the kth largest
	 * @return Selected element
	 */
	public static <T extends Comparable<? super T>> T kth_largest(
			LinkedList<T> L, int k) {
		PriorityQueue<T> S = new PriorityQueue<T>();
		//Loops through k elements of L, adding them to the PriorityQueue S.
		for (int i = 0; i < k; i++) {
			S.add(L.remove());
		}
		int lsize = L.size(); //Stores the value of size as a loop condition, because it changes during the loop.
		//Loops through the remainder of L, replacing the smallest element in S if the element in L is larger.
		for (int i = 0; i < lsize; i++) {
			T next_element = L.remove();
			if (next_element.compareTo(S.peek()) > 0) {
				S.remove();
				S.add(next_element);
			}
		}
		return S.peek();
	}

}
