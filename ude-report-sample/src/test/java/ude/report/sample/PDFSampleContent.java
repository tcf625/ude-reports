package ude.report.sample;

import java.util.Objects;

import com.iisigroup.ude.report.itext2.PDFGenerator;

public interface PDFSampleContent extends PDFGenerator {
    default PDFSampleContent andThen(final PDFSampleContent after) {
        Objects.requireNonNull(after);
        return pdfDocument -> {
            this.generatePDFContent(pdfDocument);
            after.generatePDFContent(pdfDocument);
        };
    }
}
