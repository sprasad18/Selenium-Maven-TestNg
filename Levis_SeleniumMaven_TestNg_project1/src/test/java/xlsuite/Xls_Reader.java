package xlsuite;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	public static String filename = System.getProperty("user.dir")+"\\src\\test\\resources\\xlsuite_Excel\\Suite1.xlsx";
	public String path;
	public FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public Xls_Reader(String path){
		this.path = path;
		
		try{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public int getRowCount(String sheetName){
    	int index = workbook.getSheetIndex(sheetName);
    	if(index==-1)
    		return 0;
    	else{
    		sheet = workbook.getSheetAt(index);
    		int number = sheet.getLastRowNum()+1;
    		return number;
    	}
    }
    
    //returns the data from a cell
    public String getCellData(String sheetName,String colName,int rowNum){
    	try{
    		if(rowNum<=0)
    			return "";
    	int index = workbook.getSheetIndex(sheetName);
    	int Col_Num=-1;
    	if(index==-1)
    		return "";
    	
    	sheet = workbook.getSheetAt(index);
    	row = sheet.getRow(0);
    	for(int i=0; i<row.getLastCellNum(); i++){
    		if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
    			Col_Num=i;
    	}
    	if(Col_Num==-1)
    		return "";
    	
    	sheet = workbook.getSheetAt(index);
    	row = sheet.getRow(rowNum-1);
    	if(row==null)
    		return "";
    	cell = row.getCell(Col_Num);
    	
    	if(cell==null)
    		return "";
    	//System.out.println(cell.getCellType());
    	if(cell.getCellType()==cell.CELL_TYPE_STRING){
    		return cell.getStringCellValue();
    	}else if(cell.getCellType()==cell.CELL_TYPE_BLANK)
    		return "";
    	else
    		return String.valueOf(cell.getBooleanCellValue());
    	} catch(Exception e){
    		e.printStackTrace();
    		return "row "+rowNum+" or column "+colName +" does not exist in xls";
    	}

    }
    
}
