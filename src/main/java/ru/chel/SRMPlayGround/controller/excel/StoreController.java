package ru.chel.SRMPlayGround.controller.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import ru.chel.SRMPlayGround.service.excel.store.ExcelStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/excel/store")
public class StoreController {
    private ExcelStore excelStore;
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    public StoreController(ExcelStore excelStore) {
        this.excelStore = excelStore;
    }

    @GetMapping
    public ResponseEntity<?> getStore() throws IOException {
        Workbook book = new HSSFWorkbook();
        excelStore.getExcelStore(book);

        String fileLocation = ExcelUtils.getFileName("_store.xls");

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        book.write(outputStream);
        book.close();

        // Отдача файла клиенту
        logger.info("Excel 'скачать накладные' успешно скачен.");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=allsum.xls")
                .body(resource);
    }
}
