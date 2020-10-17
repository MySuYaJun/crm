/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-20
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * <p>NAME: CreateExcelTest</p>
 * @author Administrator
 * @date 2020-06-20 10:52:57
 */
public class CreateExcelTest {
    public static void main(String[] args) throws Exception{
        //1.创建一个HSSFWorkbook的对象，对应一个excel文件
        HSSFWorkbook wb=new HSSFWorkbook();
        //2.使用wb创建一个HSSFSheet对象，对应一页
        HSSFSheet sheet=wb.createSheet("学生列表");
        //3.使用sheet创建HSSFRow对象，对应一行
        HSSFRow row=sheet.createRow(0);//rownum：表示行号，行号是从0开始，依次往后增加，0表示第一行，.....
        //4.使用row创建好几个HSSFCell对象，对应好几列
        HSSFCell cell=row.createCell(0);//colimn：表示列号，列号也是从0开始，依次往后增加，0表示第一列,.....
        cell.setCellValue("学号");
        cell=row.createCell(1);
        cell.setCellValue("姓名");
        cell=row.createCell(2);
        cell.setCellValue("年龄");

        //创建HSSFCellStyle对象，对应列的样式
        HSSFCellStyle style=wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        //5.使用sheet创建10个HSSFRow对象，对应10行
        for(int i=1;i<=10;i++){
            row=sheet.createRow(i);

            //使用每一个row创建三个HSSFCell对象，对应三列
            cell=row.createCell(0);//colimn：表示列号，列号也是从0开始，依次往后增加，0表示第一列,.....
            cell.setCellStyle(style);
            cell.setCellValue(100+i);
            cell=row.createCell(1);
            cell.setCellValue("NAME"+i);
            cell=row.createCell(2);
            cell.setCellValue(20+i);
        }

        //6.调用工具方法，生成excel文件
        OutputStream os=new FileOutputStream("F:\\teaching\\bj2002\\server-testDir\\student.xls");//如果文件不存在，插件会自动创建；但是目录必须手动创建。
        wb.write(os);
        //7.关闭资源
        os.close();
        wb.close();
        System.out.println("=======create ok=========");
    }
}
