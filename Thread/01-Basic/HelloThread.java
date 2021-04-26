public class HelloThread extends Thread {
  public HelloThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    super.run();
    System.out.println("Code runs in thread " + getName());
  }

  public static void main(String[] args) {
    HelloThread t1 = new HelloThread("Thread hello");
    t1.start();

    System.out.println("My name is: " + Thread.currentThread().getName());
  }
}