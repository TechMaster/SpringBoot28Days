import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckExcept {
  /* Cannot compile because throws Exception required
  public void readAFile1(String fileName) {
    FileReader file = new FileReader(fileName);
    BufferedReader fileInput = new BufferedReader(file);
      
    // Print first 3 lines of file "C:\test\a.txt"
    for (int counter = 0; counter < 3; counter++) 
        System.out.println(fileInput.readLine());
      
    fileInput.close();
  }
  */

  public void readAFileBetter(String fileName) throws IOException {
    FileReader file = new FileReader(fileName);
    BufferedReader bufferReader = new BufferedReader(file);
    String thisLine;
    while ((thisLine = bufferReader.readLine()) != null) {
      System.out.println(thisLine);
   }      
      
    bufferReader.close();
  }

  //method không throw mà tự xử lý Exception
  public void readAFileNotThrow(String fileName) {
    FileReader file;
    
    try {
      file = new FileReader(fileName);    
      BufferedReader bufferReader = new BufferedReader(file);
      String thisLine;
      while ((thisLine = bufferReader.readLine()) != null) {
        System.out.println(thisLine);
      }
      bufferReader.close();
    } catch (IOException e) {
      
      e.printStackTrace();
    }  
  }


  // Cách này tốt nhất
  public void readAFileBest(String fileName) throws IOException {
    FileReader file = new FileReader(fileName);
    
    //Try with resource
    try (BufferedReader bufferReader = new BufferedReader(file)) {
      String thisLine;
      while ((thisLine = bufferReader.readLine()) != null) {
        System.out.println(thisLine);
      }
    }
  }

  public void getAClass(String className) throws ClassNotFoundException {

    Class<?> c1 = Class.forName(className);
  
    System.out.println("Class represented by c1: " + c1.toString());
  }

  

}
