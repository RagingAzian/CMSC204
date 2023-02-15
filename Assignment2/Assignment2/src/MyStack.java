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
        stack = new ArrayList<T>();
    }
    public boolean isEmpty(){
        return(topIndex==-1);
    }

    public boolean isFull(){
        return(topIndex+1==size);
    }

    public T pop() throws StackUnderflowException{
        if(!isEmpty()){
            return(stack.remove(topIndex));
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
        return stack.size();
    }

    public boolean push(T e) throws StackOverflowException{
        if(size==size()){
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
            list+=e+" ";
        }
        return list;
    }

    public String toString(String delimiter){
        String list = "";
        for(T e: stack){
            list+=e+delimiter;
        }
        list = list.substring(0,list.length()-1);
        return list;
    }

    public void fill(ArrayList<T> list){
        for(T elm: list){
            try{
                this.push(elm);
            }
            catch(StackOverflowException e){
                e.printStackTrace();
            }
        }
    }
}