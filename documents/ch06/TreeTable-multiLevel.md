### 樹狀欄位群組設定

在規劃直欄表格時，可能會把相鄰的幾個欄位以群組方式呈現，以利使用者檢視報表。
在 UDE-Report 中的用例如下，也就是以 append(String title, Consumer<C>) 建立合併欄位("資料內容")後，再在 Consumer.accept() 中對該欄位增加下面的 "值1","值2" 兩個子欄位。

``` java 
metadata.append("資料內容", column -> {
    column.append("值1", new BeanProperty("value1"));
    column.append("值2", new BeanProperty("value2"));
});
```





![](/assets/ch06/treeTable-multiLevel.png)


#### **完整測試案例**

``` java 
    @Test
    public void test_columnGroup() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"));
            column.append("值2", new BeanProperty("value2"));
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

```



