package sandbox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

  /**
   * @return
   * @throws IOException
   * 
   */
  private String fileDirectory = null;
  private File file = null;

  // CONSTRUCTOR

  public WriteToFile(String directory) throws IOException {
    this.fileDirectory = directory;

    file = new File(fileDirectory);

    if (!file.exists()) {
      file.createNewFile();
    }
    FileWriter fw = new FileWriter(fileDirectory, false);
    fw.close();

  }

  public void write(String string) throws IOException {
    // System.out.println("Writing to file");

    FileWriter fw = new FileWriter(fileDirectory, true);
    BufferedWriter bw = new BufferedWriter(fw);

    bw.append(string + '\n');

    // bw.flush();
    bw.close();
    fw.close();

  }

}
