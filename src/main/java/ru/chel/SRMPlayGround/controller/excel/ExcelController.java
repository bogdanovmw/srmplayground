package ru.chel.SRMPlayGround.controller.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chel.SRMPlayGround.controller.CustomerController;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.service.DriverService;
import ru.chel.SRMPlayGround.service.excel.customer.GenerateExcelAllCustomer;
import ru.chel.SRMPlayGround.service.excel.driver.GenerateExcelSum;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    private GenerateExcelAllCustomer generateExcelAllCustomer;
    private GenerateExcelSum generateExcelSum;
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    public ExcelController(GenerateExcelAllCustomer generateExcelAllCustomer, GenerateExcelSum generateExcelSum, DriverService driverService) {
        this.generateExcelAllCustomer = generateExcelAllCustomer;
        this.generateExcelSum = generateExcelSum;
        this.driverService = driverService;
    }

    private DriverService driverService;

    @GetMapping
    public ResponseEntity<?> findById() throws IOException {
        List<Driver> drivers = driverService.findAll();

        Workbook book = new HSSFWorkbook();
        generateExcelAllCustomer.getAllCus(book, drivers);
        generateExcelSum.getSum(book);

        // Сохранение файла на диск
        String fileLocation = ExcelUtils.getFileName("_main.xls");

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        book.write(outputStream);
        book.close();

        logger.info("Excel 'скачать итог' успешно скачен.");
        // Отдача файла клиенту
        InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=allsum.xls")
                .body(resource);
    }
}
