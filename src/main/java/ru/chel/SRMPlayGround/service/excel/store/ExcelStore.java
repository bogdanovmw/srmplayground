package ru.chel.SRMPlayGround.service.excel.store;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.model.ProductList;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.excel.GenerateInteger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelStore {
    private OrdersService ordersService;
    private ExcelStoreUtils excelStoreUtils;

    public ExcelStore(OrdersService ordersService, ExcelStoreUtils excelStoreUtils) {
        this.ordersService = ordersService;
        this.excelStoreUtils = excelStoreUtils;
    }

    public void getExcelStore(Workbook book){
        List<Orders> orders = ordersService.findAll();

        HSSFCellStyle styleRight = (HSSFCellStyle) book.createCellStyle();
        HSSFCellStyle styleCenter = (HSSFCellStyle) book.createCellStyle();
        HSSFCellStyle styleLeft = (HSSFCellStyle) book.createCellStyle();

        Font fontMain = book.createFont();
        fontMain.setFontHeightInPoints((short)12);
        fontMain.setFontName("TIMES NEW ROMAN");
        fontMain.setUnderline(HSSFFont.U_SINGLE);

        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleRight.setFont(fontMain);
        styleCenter.setFont(fontMain);
        styleLeft.setFont(fontMain);

        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        Font font = book.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("TIMES NEW ROMAN");
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleSum = (HSSFCellStyle) book.createCellStyle();
        styleSum.setFont(font);
        styleSum.setBorderTop(BorderStyle.THIN);
        styleSum.setBorderBottom(BorderStyle.THIN);
        styleSum.setBorderLeft(BorderStyle.THIN);
        styleSum.setBorderRight(BorderStyle.THIN);
        styleSum.setAlignment(HorizontalAlignment.RIGHT);

        int index = 1;
        for (Orders order : orders){
            Sheet sheet = book.createSheet(order.getCustomers().getName() + " - " + index++);
            GenerateInteger.setStylePrint(sheet, true, false);

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            excelStoreUtils.createRowCellData(sheet, styleRight,  0, 5, format.format(new Date()));
            excelStoreUtils.createRowCellData(sheet, styleCenter, 1, 5, "Накладная №");
            excelStoreUtils.createRowCellData(sheet, styleLeft, 2, 5, "Кому: " +
                    ExcelStoreUtils.firstUpperCase(order.getCustomers().getName()));
            excelStoreUtils.createRowCellData(sheet, styleLeft, 3, 5, "От кого: ИП Титова");
            excelStoreUtils.createRowCellData(sheet, styleCenter, 4, 5, "");


            String[] array = {"№", "Наименование", "Заявка", "Кол-во", "Цена", "Сумма" };
            excelStoreUtils.createRowData(sheet, style, 5, array);

            int rowNum = 6, i = 1;

            for (ProductList pL : order.getListProd()){
                Row row = sheet.createRow(rowNum++);

                Cell name = row.createCell(0);
                name.setCellValue(i++);
                name.setCellStyle(style);

                name = row.createCell(1);
                name.setCellValue(pL.getProduct().getName());
                name.setCellStyle(style);

                name = row.createCell(2);
                name.setCellValue(String.valueOf(pL.getCount()).replace(".0", "") + " " + pL.getType());
                name.setCellStyle(style);

                name = row.createCell(3);
                name.setCellStyle(style);

                name = row.createCell(4);
                name.setCellStyle(style);

                name = row.createCell(5);
                name.setCellStyle(style);

                sheet.setColumnWidth(1, 30 * 256);
                sheet.setColumnWidth(2, 10 * 256);
                sheet.setColumnWidth(3, 10 * 256);
                sheet.setColumnWidth(4, 12 * 256);
                sheet.setColumnWidth(5, 12 * 256);
            }
            int lastRowNum = sheet.getLastRowNum();
            String[] arr = {"", "", "", "", "", "" };
            excelStoreUtils.createRowData(sheet, style, lastRowNum + 1, arr);
            excelStoreUtils.createRowData(sheet, style, lastRowNum + 2, arr);

            excelStoreUtils.createRowCellSum(sheet, styleSum, style, rowNum + 2, 4, "Итого: ");

            Row row = sheet.createRow(lastRowNum + 8);
            Cell cell = row.createCell(0);
            cell.setCellValue("Сдал:                           ");
            cell.setCellStyle(styleLeft);

            cell = row.createCell(4);
            cell.setCellValue("Принял:                           ");
            cell.setCellStyle(styleLeft);
        }
    }
}
