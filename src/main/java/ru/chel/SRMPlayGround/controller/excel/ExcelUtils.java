package ru.chel.SRMPlayGround.controller.excel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    public static String getFileName(String name){
        // Создание файлов
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        // Создаем папку если нет
        Path folder = Paths.get(path.substring(0, path.length() - 1) + "\\excel");
        if (!Files.exists(folder)){
            new File(String.valueOf(folder)).mkdir();
        }

        // Наименование файла
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(new Date());
        String nameFile = date + name;

        return path.substring(0, path.length() - 1) + "\\excel\\" + nameFile;
    }
}
