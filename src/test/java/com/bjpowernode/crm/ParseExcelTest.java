/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-23
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * <p>NAME: ParseExcelTest</p>
 * @author Administrator
 * @date 2020-06-23 11:35:32
 */
public class ParseExcelTest {
    public static void main(String[] args) throws Exception{
        //1.根据is创建一个HSSFWorkbook对象，对应一个excel文件，封装了文件中的所有信息
        InputStream is=new FileInputStream("F:\\teaching\\bj2002\\server-testDir\\activityList20200622115510.xls");
        HSSFWorkbook wb=new HSSFWorkbook(is);
        //2.根据wb获取HSSFSheet对象，对应一页
        HSSFSheet sheet=wb.getSheetAt(0);
        //3.根据sheet的HSSFRow对象，对应行
        HSSFRow row=null;
        HSSFCell cell=null;
        for(int i=0;i<=sheet.getLastRowNum();i++){//sheet.getLastRowNum()：最后一行的编号
            row=sheet.getRow(i);

            for(int j=0;j<row.getLastCellNum();j++){//row.getLastCellNum(): 最后一列的编号+1
                cell=row.getCell(j);

                System.out.print(getCellValueForStr(cell)+" ");
            }

            //一行打完，输出一个换行
            System.out.println();
        }
    }

    public static String getCellValueForStr(HSSFCell cell){
        String ret="";

        /*if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            ret=cell.getStringCellValue();
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            ret=cell.getBooleanCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            ret=cell.getNumericCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
            ret=cell.getCellFormula();
        }else{
            ret="";
        }*/

        switch (cell.getCellType()){
            case HSSFCell.CELL_TYPE_STRING:
                ret=cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                ret=cell.getBooleanCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                ret=cell.getNumericCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                ret=cell.getCellFormula();
                break;
            default:
                    ret="";
        }

        return ret;
    }
}