import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;


public class BasicDoubleLinkedList <T> implements Iterable<T>{ 
    protected int size;
    protected Node first;
    protected Node last;

    public BasicDoubleLinkedList(){
        size = 0;
        first = null;
        last = null;
    }

    public int getSize(){
        return size;
    }

    public void addToEnd(T data){
        if(last == null){
            last = new Node(data, null, null);
            first = last;
        } else{
            Node nextLast = new Node(data, last, null);
            last.setNext(nextLast);
            last = nextLast;
        }
        size++;
    }
    public void addToFront(T data){
        if(first == null){
            first = new Node(data, null, null);
            last = first;
        }
        else{
            Node nextFirst = new Node(data,null, first);
            first.setPrev(nextFirst);
            first = nextFirst;
        }
        size++;
    }
    public T getFirst(){
        if(first != null)
            return first.getItem();
        return null;
    }
    public T getLast(){
        if(last!=null)
            return last.getItem();
        return null;
    }
    
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
        return new DoubleLinkedListIterator();
    }

    public Node remove(T targetData, Comparator<T> comparator) {
		Node current = first;
		
		if(size == 0) {
			return current;
		}
		if(size ==1) {
			if(comparator.compare(targetData, first.getItem())==0) {
				first = null;
				last = null;
				size--;
			}
			return current;
		}
		
		while(current != null) {
			if(comparator.compare(targetData, current.getItem())==0) {
				if(current.equals(first)) {
					first = current.getNext();
					current.getNext().setPrev(null);
				} else if(current.equals(last)) {
					last = current.getPrev();
					current.getPrev().setNext(null); 
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());				
				}
				
				size--;
				break;
			}
			current=current.getNext();
		}
		return current;
	}
    public T retrieveFirstElement(){
        if(first != null){
            T t = first.getItem();
            if(size == 1){
                first = null;
                last = null;
                return t;
            }
            first = first.getNext();
            first.setPrev(null);
            return t;
        }
        return null;
    }

    public T retrieveLastElement(){
        if(last!=null){
            T t = last.getItem();
            if(size==1){
                first = null;
                last = null;
                return t;
            }
            last = last.getPrev();
            last.setNext(null);
            size--;
            return t;
        }
        return null;
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> list = new ArrayList<T>();
        DoubleLinkedListIterator iterator = (DoubleLinkedListIterator) iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    public class Node{
        private T item;
        private Node prev,next = null;
        Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
        public T getItem(){
            return item;
        }
        public Node getPrev(){
            return prev;
        }
        public Node getNext(){
            return next;
        }
        public void setNext(Node n){
            next = n;
        }
        public void setPrev(Node n){
            prev = n;
        }
        public boolean equals (Node n){
            return(next==n.getNext() && prev == n.getPrev() && item==n.getItem());
        }
    }
    
    protected class DoubleLinkedListIterator implements ListIterator<T>{
        Node node;
        Node prev;
        boolean firstNode,lastNode = false;

        DoubleLinkedListIterator(){
            node = first;
            firstNode = true;
        }
        public boolean hasNext(){
            return !lastNode;
        }
        public boolean hasPrevious(){
            return !firstNode;
        }
        public T next() throws NoSuchElementException {
			if (hasNext()) {
				if(node==null && lastNode == false) {
					node = prev;
				}
				T t = node.getItem();
				prev = node;
				node = node.getNext();
				if(node==null) {
					lastNode = true;
				} else {
					firstNode = false;
					lastNode = false;
				}
				return t;
			}
			throw new NoSuchElementException();
		}
        public T previous() throws NoSuchElementException {
			if (hasPrevious()) {
				if(node==null && firstNode == false) {
					node = prev;
				}
				T t = node.getItem();
				prev = node;
				node = node.getPrev();
				if(node==null) {
					firstNode = true;
				} else {
					firstNode = false;
					lastNode = false;
				}
				return t;
			}

			throw new NoSuchElementException();
		}
        public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public void set(T t) {
			throw new UnsupportedOperationException();
		}

		public void add(T t) {
			throw new UnsupportedOperationException();
		}
    }
}
