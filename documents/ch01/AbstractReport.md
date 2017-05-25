#  設計表樣清單定義介面

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
## AllReports

``` java
public enum AllReports implements ReportDefinition {

    GSS0010("折抵役期空白清冊"), //
    GSS0011("折抵役期資料匯出", DocumentFormat.CSV), //
    ;

    private final String reportName;
    private final Set<DocumentFormat> suppertedFormats;

    private AllReports(final String reportName) {
        this.reportName = reportName;
        this.suppertedFormats = EnumSet.of(DocumentFormat.PDF, DocumentFormat.EXCEL);
    }

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


