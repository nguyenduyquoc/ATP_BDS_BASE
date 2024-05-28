package com.anthinhphatjsc.ezisolutions.core;

import com.anthinhphatjsc.ezisolutions.exceptions.ErrorExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class BaseExcelWriter<E extends BaseEntity> {
    List<String> columnNameList;
    List<E> dList;
    List<ErrorExcel> errorList;
    String excelFilePath;
    public BaseExcelWriter(List<E> dList, List<String> columnNameList){
        this.dList = dList;
        this.columnNameList = columnNameList;
        this.excelFilePath = "./"+System.currentTimeMillis()+".xlsx";
    }
    public BaseExcelWriter(List<E> dList, List<String> columnNameList, List<ErrorExcel> errorList){
        this.dList = dList;
        this.columnNameList = columnNameList;
        this.excelFilePath = "./"+System.currentTimeMillis()+".xlsx";
        this.errorList = errorList;
    }
    private static CellStyle cellStyleFormatNumber = null;

    public Object writeExcel() throws IOException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Workbook workbook = getWorkbook();
        Sheet sheet = workbook.createSheet("sheet");

        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        for (int i=0;i<dList.size();i++) {
            Row row = sheet.createRow(rowIndex);
            writeField(sheet, i, row);
            rowIndex++;
        }
        writeFooter(sheet, rowIndex);
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
        return excelFilePath;
    }
    private Workbook getWorkbook() throws IOException {
        Workbook workbook = null;
        if (this.excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (this.excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private void writeHeader(Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        for (int columnIndex=0;columnIndex< columnNameList.size();columnIndex++){
            Cell cell = row.createCell(columnIndex);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNameList.get(columnIndex));
        }
    }
    private void writeField(Sheet sheet, int index, Row row) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        if (cellStyleFormatNumber == null) {
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        custom(sheet, index, row);
    }
    public CellStyle createStyleForContentError(Sheet sheet){
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.RED1.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    public CellStyle createStyleForContentWarning(Sheet sheet){
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW1.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    public CellStyle createStyleForContent(Sheet sheet){
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.BLACK.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    private CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private void writeFooter(Sheet sheet, int rowIndex) {
        Row row = sheet.createRow(rowIndex);
    }

    // Auto resize column width
    private void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
    public abstract void custom(Sheet sheet, int index, Row row);

}
