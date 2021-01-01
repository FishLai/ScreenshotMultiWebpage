package cc.fishlab1.multi_url_screenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HandleURLs {
	private File file;
	private String extension;
	
	public HandleURLs(String fnDir) {
		this.file = new File(fnDir);
		this.extension = jurdgeFileExtension(fnDir);
	}
	
	public ArrayList<String> doRead() throws IOException {
		File file = this.file;
		ArrayList<String> URLs = new ArrayList<String>();
		if(extension.equals("text")) {
			HandleText handle = new HandleText(file);
			URLs = handle.doRead();
		}else if(extension.equals("xlsx")) {
			HandleXlsx handle = new HandleXlsx(file);
			URLs = handle.doRead();
		}
		
		return URLs;
	}
	
	
	private String jurdgeFileExtension(String fnDir) {
		Pattern pattern;
		Matcher matcher;
		boolean isMatch;
		pattern = Pattern.compile("txt$", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(fnDir);
		isMatch = matcher.find();
		if(isMatch) {
			return "text";
		}
		
		pattern = Pattern.compile("xlsx$", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(fnDir);
		isMatch = matcher.find();
		if(isMatch) {
			return "xlsx";
		}
		
		return null;
	}
}

class HandleText {
	private File file;
	
	public HandleText(File file) {
		this.file = file;
	}
	
	public ArrayList<String> doRead() throws FileNotFoundException {
		File file = this.file;
		Scanner txt = new Scanner(file);
		String url;
		ArrayList<String> URLs = new ArrayList<String>();
		while(txt.hasNextLine()) {
			url = txt.nextLine();
			URLs.add(url);
		}
		txt.close();
		return URLs;
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
		}
		
		workbook.close();
		fIS.close();
		return urls;
	}
	
	
}
