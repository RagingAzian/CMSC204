import java.util.*;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
    private Comparator<T> comparator;
    public SortedDoubleLinkedList(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public void add (T data){
        if(size==0 || comparator.compare(head.getItem(), data)>=0){
            super.addToFront(data);
            return;
        }
        if(comparator.compare(tail.getItem(),data)<=0){
            super.addToEnd(data);
            return;
        }
        int prev = -1;
        int current;

        Node currentNode = head;
        Node newNode;

        while(true){
            current = comparator.compare(currentNode.getItem(),data);
            if(prev < 0 && current >= 0 ){
				newNode = new Node(data, currentNode.getPrev(), currentNode);
				newNode.setPrev(currentNode.getPrev());
				newNode.setNext(currentNode);
                currentNode.getPrev().setNext(newNode);
                currentNode.setPrev(newNode);
				size++;
				break;
            }
            prev = current;
            currentNode = currentNode.getNext();
        }
        return;
    }
    public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}

	public ListIterator<T> iterator() {
		return super.iterator();
	}

	public Node remove(T data, Comparator<T> comparator) {
		return (Node) super.remove(data, comparator);
	}
}