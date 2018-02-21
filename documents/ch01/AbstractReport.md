### 文件架構範例

所有範例原始碼，皆放於 github 專案：https://github.com/tcf625/ude-reports/ 以供下載。

  * 範例程式：/ude-report-sample
  * 相關產出結果：/sample-output

###  表樣清單介面及定義範例

#### 介面：ReportDefinition 

基本的規劃範例：每一張表樣應有的資訊包括「代碼」、「名稱」及「可支援輸出格式」。

``` java
public interface ReportDefinition {
    /** 代碼. */ 
    String getReportCode();
    /** 名稱. */ 
    String getReportName(); 
    /** 支援輸出格式. */
    Set<DocumentFormat> getSuppertedFormats();
    /** 也可以統一處理輸出時使用的檔名(舉例). */
    default String toFileName(final DocumentFormat format) {
        final LocalDateTime localDateTime = Now.localDateTime();
        final String time = RocDateUtils.format(localDateTime, "yyyMMdd-h-m-s");
        return getReportCode() + "_" + time + "_" + uuid() + "." + format.getExtFileName();
    }
}
```

####  定義：AllReports

這個範例只定義兩張表樣。一般正式的專案裡，表樣數量可能是數以百計，也有可能會依業務性質拆分為多個不同的 ENUM 定義。

``` java
public enum AllReports implements ReportDefinition {
    GSS0010("折抵役期空白清冊"), //
    GSS0011("折抵役期資料匯出", DocumentFormat.CSV), //
    ;

    private final String reportName;
    private final Set<DocumentFormat> suppertedFormats;

    /** 規劃預期支援的文件格式為 PDF/EXCEL. */
    private AllReports(String reportName) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(DocumentFormat.PDF, DocumentFormat.EXCEL);
    }
    
    /** 自訂支援文件格式. */ 
    private AllReports(String reportName, DocumentFormat first, DocumentFormat... rest) {
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

### 文件產出範例

#### 共用文件父類別：AbstractSampleReport

要求子類別實作所有輸出格式介面。實務上，reportDefinition、pageSize 也可用 Introduce Parameter Object 手法整合為同一參數物件。

``` java
public abstract class AbstractSampleReport extends AbstractPDFGenerator implements ExcelGenerator, CSVGenerator {

    protected final SampleReportDefinition reportDefinition;

    protected AbstractSampleReport(SampleReportDefinition reportDefinition, Rectangle pageSize) {
        this.reportDefinition = reportDefinition;
        super.setPageSize(pageSize);
    }
    
    public SampleReportDefinition getReportDefinition() {
        return this.reportDefinition;
    }
    
    // ... 後略
}
```

#### GSS0010 / GSS0011

隨意輸出簡單內容，如：

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

#### GSS0010Test / GSS0011Test

   用於執行報表程式，完整說明請參考後續「單元測試」範例。










