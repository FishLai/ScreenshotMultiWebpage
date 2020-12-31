package cc.fishlab1.multi_url_screenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HandleURLs {
	private File file;
	private ArrayList<String> URLs;
	public HandleURLs(String fnDir) {
		
		this.file = new File(fnDir);
	}
	
	public ArrayList<String> doHandleXlsx() throws IOException {
		HandleXlsx excel = new HandleXlsx(this.file);
		return excel.doRead();
	}
	
	
}

class HandleXlsx {
	private File file;
	
	public HandleXlsx(File file) {
		this.file = file;
	}
	
	public ArrayList<String> doRead() throws IOException{
		File file = this.file;
		FileInputStream fIS = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fIS);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIt = sheet.iterator();
		ArrayList<String> urls = new ArrayList<String>();
		while(rowIt.hasNext()) {
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				//System.out.print(cell.toString() +";");
				urls.add(cell.toString());
			}
			
			System.out.println();
		}
		
		workbook.close();
		fIS.close();
		
		return urls;
	}
	
	
}
