import java.util.*;


public class BasicDoubleLinkedList<T> implements Iterable<T> {
    protected int size;
    protected Node head;
    protected Node tail;

    public BasicDoubleLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize() {
        return size;
    }

    public void addToEnd(T data) {
        if (tail == null) {
            tail = new Node(data, null, null);
            head = tail;
        } else {
            Node nextLast = new Node(data, tail, null);
            tail.setNext(nextLast);
            tail = nextLast;
        }
        size++;
    }

    public void addToFront(T data) {
        if (head == null) {
            head = new Node(data, null, null);
            tail = head;
        } else {
            Node nextFirst = new Node(data, null, head);
            head.setPrev(nextFirst);
            head = nextFirst;
        }
        size++;
    }

    public T getFirst() {
        if (head != null)
            return head.getItem();
        return null;
    }

    public T getLast() {
        if (tail != null)
            return tail.getItem();
        return null;
    }

    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        return new DoubleLinkedListIterator();
    }

    public Node remove(T targetData, Comparator<T> comparator) {
        Node current = head;

        if (size == 0) {
            return current;
        }
        if (size == 1) {
            if (comparator.compare(targetData, head.getItem()) == 0) {
                head = null;
                tail = null;
                size--;
            }
            return current;
        }

        while (current != null) {
            if(comparator.compare(targetData,current.getItem())!=0)
                current = current.getNext();
            else if (current.equals(head)) {
                head = current.getNext();
                current.getNext().setPrev(null);
                size--;
                break;
            } else if (current.equals(tail)) {
                tail = current.getPrev();
                current.getPrev().setNext(null);
                size--;
                break;
            } else {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                size--;
                break;
            }
        }
        return current;
    }

    public T retrieveFirstElement(){
        if (head == null)
            return null;
        T t = head.getItem();
        if(size ==1){
            head = null;
            tail = null;
            return t;
        }
        head = head.getNext();
        head.setPrev(null);
        return t;
    }


    public T retrieveLastElement(){
        if(tail==null)
            return null;
        T t = tail.getItem();
        if(size==1){
            head = null;
            tail = null;
            return t;
        }
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
        return t;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<T>();
        DoubleLinkedListIterator iterator = (DoubleLinkedListIterator) iterator();
        while (iterator.hasNext()) 
            list.add(iterator.next());
        return list;
    }

    protected class Node {
        private T data;
        private Node prev, next;

        Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        Node(){
            data = null;
            prev = null;
            next = null;
        }

        Node(T data){
            this.data = data;
            prev = null;
            next = null;
        }

        public T getItem() {
            return data;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }

        public void setPrev(Node n) {
            prev = n;
        }

        public boolean equals(Node n) {
            return (next == n.getNext() && prev == n.getPrev() && data == n.getItem());
        }
    }

    protected class DoubleLinkedListIterator implements ListIterator<T> {
        Node next, prev = next = null;

        DoubleLinkedListIterator() {
            next = head;
        }

        public boolean hasNext() {
            return next!=null;
        }

        public boolean hasPrevious() {
            return prev!=null;
        }

        public T next() throws NoSuchElementException{
            if(!hasNext())
                throw new NoSuchElementException();
            T t = next.getItem();
            prev = next;
            next = next.next;
            return t;
        }

        public T previous() throws NoSuchElementException {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            T t = prev.getItem();
            next = prev;
            prev = prev.prev;
            return t;
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