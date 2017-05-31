


``` java
public class SectionSample extends AbstractPDFGenerator {
    private String text;
    public SectionSample(final Rectangle pageSize, final String text) {
        super(pageSize);
        this.text = text;
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.text, 28, DocumentAlign.CENTER);
    }
}
```




``` java
final SectionSample s1 = new SectionSample(PageSize.A6.rotate(), "Chap 1");
final SectionSample s2 = new SectionSample(PageSize.A6.rotate(), "Chap 2");
final SectionSample s3 = new SectionSample(PageSize.A6.rotate(), "Chap 3");
final SectionSample s4 = new SectionSample(PageSize.A6.rotate(), "Chap 4");
final SectionSample s5 = new SectionSample(PageSize.A6.rotate(), "Chap 5");
final PDFGenerator root = PDFGenerator.Helper.from(s1, s2, s3, s4, s5);
s2.addSection(new SectionSample(PageSize.A7, "Chap 2-1"));
s2.addSection(new SectionSample(PageSize.A7, "Chap 2-2"));
s2.addSection(new SectionSample(PageSize.A7, "Chap 2-3"));
root.generatePDF(getDocumentManager(), createFileByTestName(DocumentFormat.PDF));
```

