# 環境與設定

Ude-Report 基於 UDE 套件開發，一般運作於 Spring 環境之下，但需要準備的元件只有 PDFDocumentManager 及其設定檔。下例中若未以 constructor-arg 傳入設定檔資源路徑，預設將使用隨同發布的 'classpath:itext-config-default.properties'。


``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
   <constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
   
```



# 單元測試

一般建議使用 AbstractITextTestkit或是 AbstractITextTest 為父類別，實作單元測試，因為有一些附加的測試特性可以選用。

## AbstractITextTestkit

基礎的測試類別，主要功能有二：提供 PDFDocumentManager 以及依據測試METHOD 名稱產出輸出檔案物件。
建構子可傳入ITextTestConfig以定義測試設定(也可使用預設無參數設定)。

``` java
public class GSS0010Test extends AbstractITextTestkit {

    final GSS0010 gss0010 = new GSS0010();

    public GSS0010Test() {
        super(prepareConfig());
    }

    private static ITextTestConfig prepareConfig() {
        final ITextTestConfig config = new ITextTestConfig();
        config.setFixedBaseDir(".");
        config.setPath(UdeSystemPropertyItem.GLOBAL_RESOURCE_PATH, "${BASEDIR}");
        config.iTextConfigPath = "classpath:itext-config-test.properties";
        config.outputRoot = new File(FileUtils.getTempDirectory(), "__Report_Output");
        config.keepOutputFile = true;
        config.showMarginBorder = true;
        return config;
    }

    /** 產出檔名為：GSS0010Test/[GSS0010Test]PDF.pdf     */
    @Test
    public void testPDF() {
        this.gss0010.generatePDF(getDocumentManager(), createFileByTestName(DocumentFormat.PDF));
    }

    /** 產出檔名為：GSS0010Test/[GSS0010Test]Excel.xlsx  */
    @Test
    public void testExcel() {
        this.gss0010.generateExcel(createFileByTestName(DocumentFormat.EXCEL));
    }

}

```
以下是 ITextTestConfig 的可設定內容：

* iTextConfigPath: iText設定檔路徑，預設為 classpath:itext-config-default.properties。
* outputRoot : 指定輸出PDF的根目錄。預設為系統TEMP路徑下的__Report_SAMPLE子目錄。
* keepOutputFile : 執行完成後，是否保留檔案，預設為TRUE。但在 CI 環境中，應以環境變數 keepOutputFile 設定為 FALSE。
* showMarginBorder : 輸出的範例PDF，是否額外輸出邊界虛線，預設為TRUE。此項設定可以有效幫助開發人員調整版面設定。如果要產出給客戶做需求確認時，可以視需求設為FALSE。
* UDE 套件設定
  *  setFixedBaseDir(String) : 在單一測試中，抽換所使用的系統瑻境變數 BASEDIR 值。
  *  setPath(UdeSystemPropertyItem, String) : 在單一測試中，指定系統瑻境路徑變數值。
  
## AbstractITextTest 




