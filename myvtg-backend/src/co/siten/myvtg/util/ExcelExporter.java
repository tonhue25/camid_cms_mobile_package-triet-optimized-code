package co.siten.myvtg.util;

import co.siten.myvtg.model.myvtg.Agent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Agent> listAgent;

    public ExcelExporter(List<Agent> listAgent) {
        this.listAgent = listAgent;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Staffs");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();

        createCell(row, 0, "ID", style);
        createCell(row, 1, "CAM_ID", style);
        createCell(row, 2, "FULL_NAME", style);
        createCell(row, 3, "PHONE_NUMBER", style);
        createCell(row, 4, "EMPLOYEE_CODE", style);
        createCell(row, 5, "EMPLOYEE_TYPE", style);
        createCell(row, 6, "POSITION", style);
        createCell(row, 7, "DEPARTMENT", style);
        createCell(row, 8, "EMONEY_ACCOUNT_NUMBER", style);
        createCell(row, 9, "SHOWROOM_NAME", style);
        createCell(row, 10, "SHOWROOM_ADDRESS", style);
        createCell(row, 11, "STATUS", style);
        createCell(row, 12, "NOTE", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Agent agent : listAgent) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, agent.getId().toString(), style);
            createCell(row, columnCount++, agent.getCamId().toString(), style);
            createCell(row, columnCount++, agent.getFullName(), style);
            createCell(row, columnCount++, agent.getPhoneNumber(), style);
            createCell(row, columnCount++, agent.getEmployeeCode(), style);
            createCell(row, columnCount++, agent.getEmployeeType().toString(), style);

            createCell(row, columnCount++, agent.getPosition(), style);
            createCell(row, columnCount++, agent.getDepartment(), style);

            createCell(row, columnCount++, agent.getEmoneyAccountNumber(), style);
            createCell(row, columnCount++, agent.getShowroomName(), style);
            createCell(row, columnCount++, agent.getShowroomAddress(), style);
            createCell(row, columnCount++, agent.getStatus().toString(), style);

            createCell(row, columnCount++, agent.getNote(), style);
        }
    }

    public OutputStream export(HttpServletResponse response) {
        writeHeaderLine();
        writeDataLines();
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream;
    }
}
