package reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelPoi {
  // number of agent rows will be used to determine how many agents to create in the context
  private int numAgentRows = 0;

  // empty constructor
  public ReadExcelPoi() {

  }

  /**
   * Takes a file directory array of double values and agent number to perform actions on
   * 
   * @param String
   * @param ArrayList<Double>
   * @param int agentNumber
   * @return Array array of Doubles that represent the weights of each agent's variable
   */
  public ArrayList<Double> ReadExcelFile(String filepath, ArrayList<Double> agentList,
      int agentNumber) {

    // this array gets returned (arrays are used to write to CSV using openCSV
    // arrays can also be used to generate hash table
    ArrayList<Double> variableValues = new ArrayList<Double>();

    try {
      // xls file location
      InputStream input =
          ReadExcelPoi.class
              .getResourceAsStream(neuralNetworkABM.GlobalSettings.INPUT_AGENT_VARIABLE_XLS);
      POIFSFileSystem fs = new POIFSFileSystem(input);

      // TODO check file type to make it work for xlsx or csv
      // to work with xls files (xlsx is a different set)
      HSSFWorkbook wb = new HSSFWorkbook(fs);

      // first sheet
      HSSFSheet sheet = wb.getSheetAt(0);

      // Iterate over each row in the sheet
      Iterator<Row> rows = sheet.rowIterator();

      while (rows.hasNext()) {
        HSSFRow row = (HSSFRow) rows.next();

        // skip first row
        // first row is also header row, but also agentNumber starts with 1
        if (row.getRowNum() == 0) {
          if (neuralNetworkABM.GlobalSettings.DEBUG)
            System.out.println("this is row 0, need to skip");
          continue;
        }

        // for the other rows:
        // row number should equal agent number (assuming agents are added in order
        if (row.getRowNum() == agentNumber) {
          if (neuralNetworkABM.GlobalSettings.DEBUG)
            System.out.println("this is non 0 row, use for data!");
          if (neuralNetworkABM.GlobalSettings.DEBUG) System.out.println("Row #" + row.getRowNum());
          if (neuralNetworkABM.GlobalSettings.DEBUG)
            System.out.println("Agent Number = " + agentNumber);

          // increment number of agent rows
          setNumAgentRows(numAgentRows++);


          if (neuralNetworkABM.GlobalSettings.DEBUG)
            System.out.println("Agent Count = " + numAgentRows);

          // Iterate over each cell (column) in the row and print out the cell's content
          // assumes each cell (column) entry is a numerical value
          Iterator<Cell> cells = row.cellIterator();

          while (cells.hasNext()) {
            HSSFCell cell = (HSSFCell) cells.next();

            // check to make sure the agentID in xls file matches the agent number in model
            // first check is for the first column
            // first column of file should be the agentID
            if (cell.getNumericCellValue() == agentNumber) {
              if (neuralNetworkABM.GlobalSettings.DEBUG) {
                System.out.println(Integer.toString((int) (cell.getColumnIndex()))
                    + Integer.toString((int) (cell.getNumericCellValue()))
                    + Integer.toString((int) agentNumber) + " -- index-value-agent match!");
              }

              // Disregard first column since it is just ID numbers
              continue;
            }

            if (neuralNetworkABM.GlobalSettings.DEBUG)
              System.out.print("Cell #" + cell.getColumnIndex() + " = ");

            // for each cell (column) append to variableValues array to be returned if numeric
            // if cell is a string, it will print it in debug==True mode, all other type is wrong
            switch (cell.getCellType()) {
              case HSSFCell.CELL_TYPE_NUMERIC:
                double cellNumber = cell.getNumericCellValue();
                if (neuralNetworkABM.GlobalSettings.DEBUG) System.out.println(cellNumber);
                // append the value
                variableValues.add(cellNumber);
                break;

              case HSSFCell.CELL_TYPE_STRING:
                if (neuralNetworkABM.GlobalSettings.DEBUG)
                  System.out.println("string: " + cell.getStringCellValue());
                break;

              default:
                if (neuralNetworkABM.GlobalSettings.DEBUG)
                  System.out.println("unsuported cell type");
                break;
            }
          }

          // debug: prints out values read from xls file
          if (neuralNetworkABM.GlobalSettings.DEBUG) {
            for (Double number : agentList) {
              System.out.println("list contents: " + number);
            }
          }
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return variableValues;
  }

  // ****************************************
  // ********** GETTERS AND SETTERS *********
  // ****************************************

  public int getNumAgentRows() {
    return numAgentRows;
  }

  public void setNumAgentRows(int numAgentRows) {
    this.numAgentRows = numAgentRows;
  }
}
