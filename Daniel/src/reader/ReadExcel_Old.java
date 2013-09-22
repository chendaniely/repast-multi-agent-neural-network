/**
 * 
 */
package reader;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author dyc2112
 * 
 */
public class ReadExcel_Old {

	private HSSFSheet sheet;

	public ReadExcel_Old(InputStream is, int sheetNum) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		String sheetName = workbook.getSheetName(sheetNum);
		sheet = workbook.getSheet(sheetName);
		is.close();
	}

	public int getLastRowNum() {
		return sheet.getLastRowNum();
	}

	public static void main(String[] args) {
		String fileName = "agent variable weights.xlsx";

	}

}
