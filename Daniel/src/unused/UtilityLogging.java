package unused;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilityLogging {

  File file = null;
  FileWriter fileWritter = null;
  BufferedWriter bufferWritter = null;

  public UtilityLogging() {
    try {
      System.out.println(System.getProperty("user.dir"));
      file = new File("./src/sandbox/testLog.txt");

      if (!file.exists()) {
        file.createNewFile();
      }


      fileWritter = new FileWriter(file.getName(), true);
      bufferWritter = new BufferedWriter(fileWritter);
      System.out.println("Log file Created");
      writeToLog("hello");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void writeToLog(String logString) {

    try {
      bufferWritter.write(logString);
      bufferWritter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public void close() {
    try {
      bufferWritter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
