import java.util.*;

public class CarQueue {
    Queue<Integer> queue;
    Random random = new Random();

    public CarQueue() {
		queue = new LinkedList<>();
		queue.add(3);
		queue.add(0);
		queue.add(2);
		queue.add(1);
		queue.add(0);
	}

    public void addToQueue() {
        class carRuns implements Runnable {
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        int num = random.nextInt(4);
                        queue.add(num);
                        Thread.sleep(50);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        Runnable run = new carRuns();
        Thread thread = new Thread(run);
        thread.start();
    }
    public int deleteQueue() {
		return queue.remove();
	}

}
