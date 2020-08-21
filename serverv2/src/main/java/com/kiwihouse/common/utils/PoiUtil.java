package com.kiwihouse.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author 陈伟平
 * @date 2020-04-15-下午 2:04
 */
public class PoiUtil {

    public static HSSFFont getHSSFFont(HSSFWorkbook workbook,String fontName,int fontSize){
        HSSFFont font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontSize);
        return font;
    }

    public static HSSFFont getHSSFFont(HSSFWorkbook workbook,int fontSize){
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short)fontSize);
        return font;
    }

    public static HSSFFont getHSSFFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short)14);
        return font;
    }

    public static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook workbook,HSSFFont font){
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);   //垂直对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);         //水平对齐
        cellStyle.setFont(font);
        return cellStyle;
    }

}
