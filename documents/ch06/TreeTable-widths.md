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
metadata.append("年度", new BeanProperty("text1"), 1);
metadata.append("地區", new BeanProperty("text2"), 2);
metadata.append("項目", new BeanProperty("text3"), 3);
metadata.append("資料內容", column -> {
  column.append("值1", new BeanProperty("value1"), 1);
  column.append("值2", new BeanProperty("value2"), 1);
});
metadata.append("資料內容", 10, column -> {
  column.append("值3", new BeanProperty("value1"), 1);
  column.append("值4", new BeanProperty("value2"), 1);
});
```

#### 產出結果

![](/assets/ch06/treeTable-width.png)

#### WidthUnit

metadata.setWidthUnit\(LengthUnit.ExcelPoint\);



#### 產出結果\(WidthUnit\)

![](/assets/ch06/treeTable-widthUnit.png)





#### 實作說明

```
    @Test
    public void test_widths() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 5);
        metadata.append("地區", new BeanProperty("text2"), 10);
        metadata.append("項目", new BeanProperty("text3"), 15);
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"), 5);
            column.append("值2", new BeanProperty("value2"), 5);
        });
        metadata.append("資料內容", 30, column -> {
            column.append("值3", new BeanProperty("value1"), 5);
            column.append("值4", new BeanProperty("value2"), 5);
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



