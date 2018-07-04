### 欄位寬度定義

以 TableMetadata 定義表格，基本上欄寬皆是以權重方式定義。  
append 函式中，基本相闗的組合如下，皆是多了一個 float 參數以定義寬度權重。

```java
// 基本新增用法
append(String title, float widthWeight)
append(String title, CellDataSource source, float widthWeight)
append(CellDataSource source, float widthWeight)

// 新增後，以 Consumer 操作所加入的欄位描述資訊
append(String title, float widthWeight, Consumer<C>)
append(String title, CellDataSource source, float widthWeight, Consumer<C>)
```

#### 實作說明

* 上層節點寬度為下層節點的總合。
* 另外對上層節點設定寬度沒有作用。

```java
metadata.append("年度", new BeanProperty("text1"), 10);
metadata.append("地區", new BeanProperty("text2"), 20);
metadata.append("項目", new BeanProperty("text3"), 30);
metadata.append("資料內容", column -> {
  column.append("值1", new BeanProperty("value1"), 10);
  column.append("值2", new BeanProperty("value2"), 10);
});
metadata.append("資料內容", 10, column -> {
  column.append("值3", new BeanProperty("value1"), 10);
  column.append("值4", new BeanProperty("value2"), 10);
});
```

#### 產出結果

可以看到第二組資料內容(值3/值4)的總計寬度，與第一組資料內容(值1/值2)一模一樣；也與「地區」欄位寬度相同。因為它們所佔權重的總合皆為 20 。

![](/assets/ch06/treeTable-width.png)

#### WidthUnit

在上例中，表格轉換的總計欄寬以頁面寬度的百分比為準(預設為100%)。

但有時會需要以更明確的單位規劃表格，如公分、如 Excel 中的欄寬單位。
這時可以使用 metadata.setWidthUnit\(LengthUnit\) 函式設定。
若寬度總和小於頁面範圍，可以用 setHorizontalAlignment 將預設置中改換為靠左或靠右輸出。

``` java
    // FOR PDF
    metadata.setWidthUnit(LengthUnit.MM);
    metadata.setHorizontalAlignment(DocumentAlign.LEFT);
    // FOR Excel
    metadata.setWidthUnit(LengthUnit.ExcelPoint);
});
```
#### 產出結果\(WidthUnit\)

![](/assets/ch06/treeTable-widthUnit.png)

#### **完整測試案例**




``` java
    @Test
    public void test_widths() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 10);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 30);
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"), 10);
            column.append("值2", new BeanProperty("value2"), 10);
        });
        metadata.append("資料內容", 10, column -> {
            column.append("值3", new BeanProperty("value1"), 10);
            column.append("值4", new BeanProperty("value2"), 10);
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
    
    

    @Test
    public void test_widthsUnit() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 10);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 30);
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"), 10);
            column.append("值2", new BeanProperty("value2"), 10);
        });
        metadata.append("資料內容", 10, column -> {
            column.append("值3", new BeanProperty("value1"), 10);
            column.append("值4", new BeanProperty("value2"), 10);
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {

            metadata.setWidthUnit(LengthUnit.MM);
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());

            pdfDocument.newPage();
            metadata.setHorizontalAlignment(DocumentAlign.LEFT);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            metadata.setWidthUnit(LengthUnit.ExcelPoint);
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }    
```



