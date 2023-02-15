public class QueueOverflowException extends Exception{
    public QueueOverflowException(){
        super("Can't use enqueue on a full queue");
    }
}
