/* 
Runnable là một interface
*/

public class DemoRunnable implements Runnable {

  public void run() {
    System.out.println("My name is: " + Thread.currentThread().getName());
  }
  public static void main(String[] args) {
    Runnable task = new DemoRunnable();
    Thread t2 = new Thread(task);
    t2.start();

    System.out.println("My name is: " + Thread.currentThread().getName());
  }
}