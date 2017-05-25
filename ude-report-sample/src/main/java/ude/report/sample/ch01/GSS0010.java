package ude.report.sample.ch01;

import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;

import com.iisigroup.ude.report.excel.ExcelDocument;
import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.lowagie.text.PageSize;

public class GSS0010 extends AbstractReport {

    public GSS0010() {
        super(AllReports.GSS0010, PageSize.A4);
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        pdfDocument.writeText("TEST-GSS0010");
    }

    @Override
    public void generateExcelContent(final ExcelDocument<?, ?> document) {
        final ExcelSheet<?> sheet = document.createSheet(this.toExcelSheetName());
        sheet.appendCell(new ExcelPoint(0, 0), "TEST-GSS0010", new CellFormat(Border.BOX));
        sheet.setColumnWidth(0, 20);
    }

    @Override
    public void generateCSVContent(final CSVPrinter csvPrinter) throws IOException {
        throw new UnsupportedOperationException();
    }

}
