import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{
    ArrayList<T> stack;
    int topIndex = -1;
    int size;

    public MyStack(int size){
        this.size = size;
        stack = new ArrayList<T>(this.size);
    }
    public MyStack(){
        stack = new ArrayList<T>(size);
    }
    public boolean isEmpty(){
        return(topIndex==-1);
    }

    public boolean isFull(){
        return(topIndex+1==size);
    }

    public T pop() throws StackUnderflowException{
        if(!isEmpty()){
            topIndex--;
            return(stack.set(topIndex+1,null));
        }
        throw new StackUnderflowException();
    }

    public T top() throws StackUnderflowException{
        if(!isEmpty()){
            return(stack.get(topIndex));
        }
        throw new StackUnderflowException();
    }

    public int size(){
        return topIndex+1;
    }

    public boolean push(T e) throws StackOverflowException{
        if(isFull()){
            throw new StackOverflowException();
        }
        topIndex++;
        if(topIndex<stack.size()){
            stack.set(topIndex, e);
        }
        else
            stack.add(e);
        return true;
    }

    public String toString(){
        String list = "";
        for(T e: stack){
            if(e!=null)
                list+=e;
        }
        return list;
    }

    public String toString(String delimiter){
        String list = "";
        for(T e: stack){
            if(e!=null)
                list+=e+delimiter;
        }
        list = list.substring(0,list.length()-1);
        return list;
    }

    public void fill(ArrayList<T> list){
        ArrayList<T> copy = new ArrayList<>();;
    	for(T item: list)
    		copy.add(item);
    	
    	for(T item: copy){
            try {
                push(item);
            }
            catch(StackOverflowException e){
                e.printStackTrace();
            }
        }
    }
}