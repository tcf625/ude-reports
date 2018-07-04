package ude.report.sample.ch01;

import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;

import com.iisigroup.ude.report.excel.ExcelDocument;
import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.lowagie.text.PageSize;

public class PageMargin extends AbstractSampleReport {

    private String text = "PageMargin";

    public PageMargin() {
        super(AllReports.GSS0010, PageSize.A3);
    }

    @Override
    protected LayoutInfo createLayoutInfo() {
        // TODO Auto-generated method stub
        return super.createLayoutInfo();
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.text);
    }

    @Override
    public void generateExcelContent(final ExcelDocument<?, ?> document) {
        final ExcelSheet<?> sheet = super.createExcelSheet(document);
        sheet.appendCell(new ExcelPoint(0, 0), this.text, new CellFormat(Border.BOX));
        sheet.setColumnWidthInPixel(0, 20);
    }

    @Override
    public String toExcelSheetName() {
        return this.text;
    }

    @Override
    public void generateCSVContent(final CSVPrinter csvPrinter) throws IOException {
        throw new UnsupportedOperationException();
    }

}
