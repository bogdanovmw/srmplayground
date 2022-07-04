package ru.chel.SRMPlayGround.service.excel.customer;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.service.excel.GenerateInteger;

import java.util.List;

@Service
public class GenerateExcelAllCustomer {
    private ExcelAllCustomer excelAllCustomer;

    public GenerateExcelAllCustomer(ExcelAllCustomer excelAllCustomer) {
        this.excelAllCustomer = excelAllCustomer;
    }

    public void getAllCus(Workbook book, List<Driver> drivers) {
        HSSFCellStyle style1 = (HSSFCellStyle) book.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER);

        style1.setBorderTop(BorderStyle.THIN);
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);

        Font font = book.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("TIMES NEW ROMAN");
        style1.setFont(font);

        int index = 1;
        for (Driver dr : drivers){
            if (!dr.getOrders().isEmpty()){
                List<List<String>> data = excelAllCustomer.getExcelAllCustomer(dr.getId());
                Sheet sheet = book.createSheet(dr.getName() + " - " + index++);
                GenerateInteger.setStylePrint(sheet, false, true);

                for (int i = 0; i < data.size(); i++) {
                    Row row = sheet.createRow(i);
                    for (int y = 0; y < data.get(i).size(); y++) {
                        Cell name = row.createCell(y);

                        if (NumberUtils.isCreatable(data.get(i).get(y))){
                            name.setCellValue(Double.parseDouble(data.get(i).get(y)));
                        }else{
                            name.setCellValue(data.get(i).get(y));
                        }

                        name.setCellStyle(style1);
                        sheet.autoSizeColumn(y);
                    }
                }
            }
        }
    }
}
