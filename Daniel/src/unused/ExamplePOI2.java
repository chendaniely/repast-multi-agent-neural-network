package unused;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExamplePOI2 {

	public static void main(String[] args) {
		System.out.println("==========");
		int cellIndex = 0;

		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			System.out.println("Row #" + row.getRowNum());

			// Iterate over each cell in the row and print out the cell's
			// content
			Iterator cells = row.cellIterator();
			while (cells.hasNext()) {
				HSSFCell cell = (HSSFCell) cells.next();
				// System.out.println( "Cell #" + cell.getCellNum() );
				// //getCellNum is depreciated
				System.out.println("Cell #" + cell.getColumnIndex());
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					System.out.println(cell.getNumericCellValue());
					list.add((double) cell.getNumericCellValue());
					for (Double number : list) {
						System.out.println("list contents: " + number);
					}
					break;
				case HSSFCell.CELL_TYPE_STRING:
					System.out.println(cell.getStringCellValue());
					break;
				default:
					System.out.println("unsuported sell type");
					break;
				}
				cellIndex++;
			}
			rowIndex++;
		}

		System.out.println("==========");

		Sheet sheet1 = wb.getSheetAt(0);
		for (Row row : sheet1) {
			for (Cell cell : row) {
				System.out.print(row);
				System.out.println(cell.getStringCellValue());
			}
		}


	}

}
