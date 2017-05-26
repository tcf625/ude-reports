
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


## 換頁

要手動對PDF進行換頁，請呼叫 pdfDocument.newPage()，若當前頁面還沒有實際內容輸出，不會真的換頁。所以如果要輸出空白頁面，請在兩次換頁間插入一次 writeText(“”)，或用 newPage(true)。



```
@Test
public void test_newPage() {
    super.createPDF(pdfDocument -> {
        pdfDocument.setPageSize(PageSize.A6.rotate());
        // P1
        pdfDocument.writeText("P1");
        pdfDocument.newPage();

        // 進入 P2
        pdfDocument.newPage(); // 連續兩次呼叫兩次 newPage() 不會有空白頁
        pdfDocument.writeText("P2");
        pdfDocument.newPage();

        // 進入 P3
        pdfDocument.newPage();
        pdfDocument.newPage();
        pdfDocument.newPage(true); // 強制換頁後，才進入第四頁

        // 進入 P4
        pdfDocument.writeText("P4. 我是第四頁");
    });
}

```





輸出內容後，如果要確認是否造成換頁，可以用pdfDocument.isPageChanged()、pdfDocument.isNewPageBegin() 兩個Method 進行判斷。當PageChanged為真，則換頁事件有被觸發。當NewPageBegin為真，表示還沒有任何內容輸出到新的頁面。



頁面大小與邊界，在文件生成的過程中是可以改變的。當目前頁面已有內容輸出時，頁面變動會在下一頁才生效。但是邊界變動可能會導致部分頁首頁尾輸出不如預期，使用時請自行確認當下文件輸出狀態。換言之，建議把PageSize與LayoutInfo的異動緊接在newPage() 之後進行。

但是一般情況下，不建議做出頁面大小變動，對於使用者要列印輸出或其它處理可能會有困擾。



