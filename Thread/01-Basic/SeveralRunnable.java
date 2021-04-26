import java.util.Random;

class RunnableTask implements Runnable {
  public void run() {
    Random random = new Random();
    int delay = random.nextInt(500);
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("task run at thread " +  Thread.currentThread().getName());
  }
}

public class SeveralRunnable {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      Runnable task = new RunnableTask();
      Thread t = new Thread(task);
      t.start();
    }

    System.out.println("My name is: " + Thread.currentThread().getName());
  }
}