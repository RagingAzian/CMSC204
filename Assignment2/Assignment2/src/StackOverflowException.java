public class StackOverflowException extends Exception{
    public StackOverflowException(){
        super("Push can't be used on a full stack");
    }
}
