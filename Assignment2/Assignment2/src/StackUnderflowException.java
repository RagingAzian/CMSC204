public class StackUnderflowException extends Exception{
    public StackUnderflowException(){
        super("Can't use top or pop on an empty stack");
    }
}