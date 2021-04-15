/* Ví dụ chứng minh Exception sẽ nới phát sinh xuống dần 
những phương thức dưới ngắn xếp call stack cho đến khi được bắt bởi try catch
*/

public class ChernobylDisaster {
  public void runUkraine() throws FireException {
    System.out.println("Chernobyl in Ukraina");
    runChernobyl();
  } 

  public void runChernobyl() throws FireException{
    System.out.println("A radiator in Chernobyl");
    runRadiator();
  }

  public void runRadiator() throws FireException {
    System.out.println("Suddenly a machine set fire");
    throw new FireException("Machine XYZ set fire!");
  }
}
