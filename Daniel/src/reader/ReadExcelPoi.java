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

public class ReadExcelPoi {
	private int numAgentRows = 0;
	
	public int getNumAgentRows() {
		return numAgentRows;
	}

	public void setNumAgentRows(int numAgentRows) {
		this.numAgentRows = numAgentRows;
	}

	public ArrayList<Double> ReadExcelFile(String filepath, ArrayList<Double> agentList, int agentNumber) {
	    ArrayList<Double> temp = new ArrayList<Double>();
	    try {
		    	
			InputStream input = ReadExcelPoi.class
					.getResourceAsStream(neuralNetworkABM.GlobalSpaceConstant.agentVariableWeightLocation);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();

				if (row.getRowNum() == 0) {
					System.out.println("this is row 0, need to skip");
					continue;
				}
				if (row.getRowNum() == agentNumber) {
					System.out.println("this is non 0 row, use for data!");
					System.out.println("Row #" + row.getRowNum());
					System.out.println("Agent Number = " + agentNumber);
					setNumAgentRows(numAgentRows ++);
					System.out.println("Agent Count = " + numAgentRows);
					// Iterate over each cell in the row and print out the
					// cell's
					// content
					Iterator cells = row.cellIterator();
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell) cells.next();
						// System.out.println ( "Cell #" + cell.getCellNum() );
						// //getCellNum is depreciated
						if (cell.getNumericCellValue() == agentNumber) {
							System.out.println(Integer.toString((int) (cell
									.getColumnIndex()))
									+ Integer.toString((int) (cell
											.getNumericCellValue()))
									+ Integer.toString((int) agentNumber)
									+ " -- index-value-agent match!");
							continue;
						}
						System.out.print("Cell #" + cell.getColumnIndex()
								+ " = ");
						
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							double cellNumber = cell.getNumericCellValue();
							System.out.println(cellNumber);
							// list.add((double) cell.getNumericCellValue());
							// for (Double number : list) {
							// System.out.println("list contents: " + number);
							// }
							//neuralNetworkABM.AgentABM.appendDoubleToVariableList(cellNumber);
							temp.add(cellNumber);
							break;
						case HSSFCell.CELL_TYPE_STRING:
							System.out.println("string: "
									+ cell.getStringCellValue());
							break;
						default:
							System.out.println("unsuported cell type");
							break;
						}
					}
					for (Double number : agentList) {
						System.out.println("list contents: " + number);
					}
				}

			}

		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	    return temp;
	}
	
}
