
## 頁面大小

以PDFDocumeneManager產生PDFDocument時，即需設定頁面大小。
若使用AbstractPDFGenerator，預設為 A4 直印，但建議一律在建構子中明確設定。

``` java
public abstract class AbstractRisReport<DEF extends ReportDefinition> //
        extends AbstractPDFGenerator {
    // ...
    public AbstractRisReport(final DEF reportDef, final Rectangle pageSize) {
        super(pageSize);
        this.reportDef = reportDef;
    }

```

## 頁面邊界

頁面邊界經由 LayoutInfo 物件設定，可以在產生PDFDocument時傳入設定或另行設值。
在所有的範例輸出結果中，都可以經由邊界虛線看出設定效果。









![](/assets/ch02/pages_margin-small.png)

``` java
@Test
public void test_Margin() {
    super.createPDF(pdfDocument -> {
        // ! 定義頁面大小.
        pdfDocument.setPageSize(PageSize.A4.rotate());
        // ! 定義四周邊界大小.
        final float marginLeft = LengthUnit.CM.trans(2.54f);  // 以 公分為單位
        final float marginRight = 36;                         // 以 pixel 為單位
        final float marginTop = LengthUnit.MM.trans(12.7f);   // 以 公厘為單位
        final float marginBottom = LengthUnit.INCH.trans(1);  // 以英吋為單位
        final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);

        // ! 定義上下頁首尾間距.
        layoutInfo.setHeaderExtra(36);
        layoutInfo.setFooterExtra(72);

        // ! 設定版面資訊
        pdfDocument.setLayoutInfo(layoutInfo);
    });
}
```


