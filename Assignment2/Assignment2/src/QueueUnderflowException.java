public class QueueUnderflowException extends Exception{
    public QueueUnderflowException(){
        super("Can't use dequeue on an empty queue");
    }
}
