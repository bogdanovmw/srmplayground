package ru.chel.SRMPlayGround.service.excel;

import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;

public class GenerateInteger {
    public static String get(double sum, String type){
        String res = "";
        if (sum < 1){
            if (type.equals("кг")){
                res = ((sum * 1000)) + " " + type;
            }else{
                res = ((int)(sum * 1000)) + " " + type;
            }
        }else{
            res = (int) sum + " " + type;
        }

        return res;
    }

    public static String getSum(double sum, int index){
        String result;
        if (sum < 1) sum = sum * 1000;
        if (sum - ((int) sum) != 0) result = "поз-" + index;
        else result = String.valueOf(sum);
        return result;
    }

    public static void setStylePrint(Sheet sheet, boolean horizontal, boolean landscape){
        sheet.setMargin(Sheet.RightMargin, 0);
        sheet.setMargin(Sheet.LeftMargin, 0);
        sheet.setMargin(Sheet.BottomMargin, 0);
        sheet.setMargin(Sheet.TopMargin, 0);
        sheet.setHorizontallyCenter(horizontal);
        sheet.getPrintSetup().setLandscape(landscape);
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
    }
}