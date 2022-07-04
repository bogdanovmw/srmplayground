package ru.chel.SRMPlayGround.service.excel.store;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

@Service
public class ExcelStoreUtils {
    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void createRowCellData(Sheet sheet, HSSFCellStyle style, int rowNum, int colLast, String data){
        Row row = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0, colLast));

        Cell cell = row.createCell(0);
        cell.setCellValue(data);
        cell.setCellStyle(style);
    }

    public void createRowCellSum(Sheet sheet, HSSFCellStyle styleSum, HSSFCellStyle style, int rowNum, int colLast, String data){
        Row row = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0, colLast));

        for (int i = 0; i <= colLast; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(data);
            cell.setCellStyle(styleSum);
        }

        Cell cell = row.createCell(5);
        cell.setCellValue("");
        cell.setCellStyle(style);
    }

    public void createRowData(Sheet sheet, HSSFCellStyle style, int rowInt, String[] array){
        Row row = sheet.createRow(rowInt);
        Cell c1 = row.createCell(0);
        c1.setCellValue(array[0]);
        c1.setCellStyle(style);
        c1 = row.createCell(1);
        c1.setCellValue(array[1]);
        c1.setCellStyle(style);
        c1 = row.createCell(2);
        c1.setCellValue(array[2]);
        c1.setCellStyle(style);
        c1 = row.createCell(3);
        c1.setCellValue(array[3]);
        c1.setCellStyle(style);
        c1 = row.createCell(4);
        c1.setCellValue(array[4]);
        c1.setCellStyle(style);
        c1 = row.createCell(5);
        c1.setCellValue(array[5]);
        c1.setCellStyle(style);
    }
}
