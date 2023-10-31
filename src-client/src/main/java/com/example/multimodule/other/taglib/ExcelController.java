package com.example.multimodule.other.taglib;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Excel 을 일고 다운로드 시키기 위한 poi 라이브러리 활용
 */
@Controller
public class ExcelController {


    @GetMapping(value = "/excel")
    public void excelParsing(ExcelDTO excelDTO) throws IOException {
        MultipartFile file = excelDTO.getMultipartFile();

        Workbook workbook = new XSSFWorkbook(file.getInputStream()); // 엑셀 파일 파싱
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet sheet = workbook.getSheetAt(0); // 엑셀 파일의 첫번째 (0) 시트지
        int rows = sheet.getPhysicalNumberOfRows(); // 행의 수

        for (int r = 2; r < rows + 2; r++) {
            Row row = sheet.getRow(r); // 0 ~ rows

            if (row != null) { // 행이 비어있지 않을 때
                int cells = row.getPhysicalNumberOfCells(); // 열의 수

                for (int c = 1; c < cells + 1; c++) {
                    Cell cell = row.getCell(c); // 0 ~ cell
                    String value = "";

                    if (cell == null) { // r열 c행의 cell이 비어있을 때
                        continue;
                    } else { // 타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value = cell.getNumericCellValue() + "";
                                break;
                            case STRING:
                                value = cell.getStringCellValue() + "";
                                break;
                            case BLANK:
                                value = cell.getBooleanCellValue() + "";
                                break;
                            case ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                        }

                    }
                    System.out.println(r + "번 행 : " + c + "번 열 값은: " + value);
                }
            }
        }
    }


}

@Getter
@Setter
class ExcelDTO {

    private MultipartFile multipartFile;

}
