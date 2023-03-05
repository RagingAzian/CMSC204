
/**Class A generic sorted double linked list will be constructed using a provided Comparator to determine how the list is to be sorted.
 * @author: Justin Nguyen
 * @param <T>
 */

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator;

	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * 
	 * @param data
	 */
	public void add(T data) {
		if (size == 0 || comparator.compare(head.getItem(), data) >= 0) {
			super.addToFront(data);
			return;
		}
		if (comparator.compare(tail.getItem(), data) <= 0) {
			super.addToEnd(data);
			return;
		}

		Node currentNode = head;
		Node newNode;

		while (true) {
			int compareResult = comparator.compare(currentNode.getItem(), data);
			if (compareResult >= 0) {
				newNode = new Node(data, currentNode.getPrev(), currentNode);
				currentNode.getPrev().setNext(newNode);
				currentNode.setPrev(newNode);
				size++;
				break;
			}
			if (currentNode == tail) {
				super.addToEnd(data);
				break;
			}
			currentNode = currentNode.getNext();
		}
	}

	/**
	 * 
	 */
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * 
	 */
	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 */
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	/**
	 * @param data
	 * @param comparator
	 * @return
	 */

	public Node remove(T data, Comparator<T> comparator) {
		return (Node) super.remove(data, comparator);
	}
}