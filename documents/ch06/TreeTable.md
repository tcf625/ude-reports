### 樹狀表格

```java
    @Test
    public void test_basicTable() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 20);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 20);
        metadata.append("值1", new BeanProperty("value1"), 20);
        metadata.append("值2", new BeanProperty("value2"), 20);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }
```

上例分別用同一個 TreeTableMetadata 轉換表格資料為 PDF / EXCEL 輸出。  
可以看到在 PDF 中，每一頁會重複顯示標題部分；而 Excel 則否。

![](/assets/ch06/basicTreeTable.png)
![](/assets/ch06/basicTreeTableExcel.png)


```java
    @Test
    public void test_basicCaption() {
        final TreeTableMetadata metadata = new TreeTableMetadata("標題");
    // ...
```

同一個範例中，若建構 TreeTableMetadata 時，加入字串參數，可以指定無框線的標題文字。
此標題預設置中、以14粗體字型顯示，若要進一步有更多變化，請參考「額外區塊」一節說明。

![](/assets/ch06/basicTreeTable-caption.png)

