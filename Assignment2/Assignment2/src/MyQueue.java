import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

    ArrayList<T> queue = new ArrayList<T>();
    int topIndex = -1;
    int size;

    public MyQueue(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return (topIndex == -1);
    }

    public boolean isFull() {
        return (topIndex+1 == size);
    }

    public T dequeue() throws QueueUnderflowException {
        if (!isEmpty()){
            topIndex--;
            return(queue.remove(0));
        }
        throw new QueueUnderflowException();
    }

    public int size() {
        return topIndex + 1;
    }

    public boolean enqueue(T e) throws QueueOverflowException {
        if (!isFull()){
            topIndex++;
            queue.add(e);
            return true;
        }
        throw new QueueOverflowException();
    }

    public String toString() {
        String list = "";
        for (T t : queue) {
            if (t != null)
                list += t;
        }
        return list;
    }

    public String toString(String delimiter) {
        String list = "";
        for (T t : queue) {
            if (t != null)
                list += t + delimiter;
        }
        list = list.substring(0,list.length()-1);
        return list;
    }

    public void fill(ArrayList<T> list) {
        ArrayList<T> copy = new ArrayList<T>();
        for (T t : list) {
            copy.add(t);
        }
        for (T t : copy) {
            try {
                enqueue(t);
            } catch (QueueOverflowException e) {
                e.printStackTrace();
            }
        }
    }
}