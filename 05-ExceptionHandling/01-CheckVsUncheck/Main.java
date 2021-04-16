import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void checkVsUncheck() {
    System.out.println("Checked vs Unchecked Exception");
    //Demo checked exception
    CheckExcept checkExcept = new CheckExcept();
    try {
      checkExcept.readAFileBest("./main.java");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    checkExcept.readAFileNotThrow("./main.java2");
    try {
      checkExcept.getAClass("UncheckExcept2");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }

    //-------------
    System.out.println("Code sau đó vẫn chạy ngon");

    UncheckExcept uncheck = new UncheckExcept();
    uncheck.setWeight(-2);
    uncheck.divide(10, 0);
    try {
      var arrStr = new ArrayList<>(List.of("One", "Two", "Three"));
      System.out.println(arrStr.get(4));

    } catch (IndexOutOfBoundsException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public static void demoChernobyl() {
    ChernobylDisaster chernobyl = new ChernobylDisaster();
    try {
      chernobyl.runUkraine();
    } catch (FireException e) {
      e.printStackTrace();
    }
  }

  public static void snakeEnterBuilding() {
    Building building = new Building();
    building.openABuilding();
  }
  public static void main(String[] args) {
    //checkVsUncheck();
    //demoChernobyl();
    snakeEnterBuilding();
  }
}