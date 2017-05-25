#  設計表樣清單定義介面


原始程式(github)：[AbstractReport](https://github.com/tcf625/ude-reports/tree/master/ude-report-sample/src/main/java/ude/report/sample/ch01)
輸出結果 : [PDF/Excel/CSV](https://github.com/tcf625/ude-reports/tree/master/sample-output/ch01)




## ReportDefinition 

基本的規劃範例，每一張表樣應有的資訊可能包括「代碼」、「名稱」及「可支援輸出格式」。

``` java
public interface ReportDefinition {
    String getReportCode(); 
    String getReportName();
    Set<DocumentFormat> getSuppertedFormats();
    /** 也可以統一在此處理輸出的檔案名稱. */
    default String toFileName(final DocumentFormat format) {
        final LocalDateTime localDateTime = Now.localDateTime();
        final String timeString = RocDateUtils.format(localDateTime, "yyyMMddhms");
        return getReportCode() + "_" + timeString + "." + format.getExtFileName();
    }
}
```

##  AllReports

這個範例中定義了兩張表樣，一般正式的專案裡，表樣數量可能是數以百計，有可能會再依業務性質拆分為不同的 ENUM 定義。

``` java
public enum AllReports implements ReportDefinition {
    GSS0010("折抵役期空白清冊"), //
    GSS0011("折抵役期資料匯出", DocumentFormat.CSV), //
    ;

    private final String reportName;
    private final Set<DocumentFormat> suppertedFormats;

    /** 規劃預期支援的文件格式為 PDF/EXCEL. */
    private AllReports(final String reportName) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(DocumentFormat.PDF, DocumentFormat.EXCEL);
    }
    /** 自訂支援文件格式. */ 
    private AllReports(final String reportName, final DocumentFormat first, final DocumentFormat... rest) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(first, rest);
    }

    @Override
    public String getReportName() {
        return this.reportName;
    }

    @Override
    public String getReportCode() {
        return name();
    }

    @Override
    public Set<DocumentFormat> getSuppertedFormats() {
        return this.suppertedFormats;
    }

}
```

## AbstractReport



``` java
public abstract class AbstractReport extends AbstractPDFGenerator implements ExcelGenerator, CSVGenerator {

    protected final ReportDefinition reportDefinition;

    protected AbstractReport(final ReportDefinition reportDefinition, final Rectangle pageSize) {
        this.reportDefinition = reportDefinition;
        super.setPageSize(pageSize);
    }
    
    public ReportDefinition getReportDefinition() {
        return this.reportDefinition;
    }
    
    // ... 後略
}
```

## GSS0010 / GSS0011

最簡單的輸出內容程式，如：

``` java 
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
        csvPrinter.print("TEST-GSS0010");
        csvPrinter.print("TEST-GSS0011");
        csvPrinter.print("TEST-GSS0012");
        csvPrinter.println();
    }
```









