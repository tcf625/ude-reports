package ude.report.sample.ch01;

import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;

import com.iisigroup.ude.report.excel.ExcelDocument;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.lowagie.text.PageSize;

public class GSS0011 extends AbstractSampleReport {

    public GSS0011() {
        super(AllReports.GSS0011, PageSize.A4);
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateExcelContent(final ExcelDocument<?, ?> document) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateCSVContent(final CSVPrinter csvPrinter) throws IOException {
        csvPrinter.print("TEST-GSS0010");
        csvPrinter.print("TEST-GSS0011");
        csvPrinter.print("TEST-GSS0012");
        csvPrinter.println();
    }

}
