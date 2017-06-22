### 樹狀欄位群組設定

在規劃直欄表格時，可能會把相鄰的幾個欄位以群組方式呈現，以利使用者檢視報表。

#### 實作說明

在 UDE-Report 中的用例如下，也就是以 append(String title, Consumer<C>) 建立合併欄位("資料內容")後，再在 Consumer.accept() 中，對該欄位增加 "值1","值2" 兩個子欄位。

``` java 
metadata.append("資料內容", column -> {
    column.append("值1", new BeanProperty("value1"));
    column.append("值2", new BeanProperty("value2"));
});
```

在 JDK 7 以前的寫法則如下例所示，若只有一兩個欄位有後續資料要設定時還好，但東西一多，會多出不少 Local variables ，程式的可讀性會略差一些。

``` java 
final TreeColumnMetadata column1 = metadata.append("資料內容");
column1.append("值1", new BeanProperty("value1"));
column1.append("值2", new BeanProperty("value2"));
final TreeColumnMetadata column2 = metadata.append("資料內容");
column2.append("值3", new BeanProperty("value3"));
column2.append("值4", new BeanProperty("value4"));
```

##### 產出結果

執行產出如下，沒有子欄位的標題項目，就會變成跨列欄位。
![](/assets/ch06/treeTable-multiLevel.png)

#### 欄位分割

若新增子欄位時，皆沒有再給定欄位標題，則效果會變成僅標題欄位跨欄合併。

``` java
final TreeColumnMetadata areaGroup = metadata.append("年度、地區");
areaGroup.append(new BeanProperty("text1"));
areaGroup.append(new BeanProperty("text2"));
// ! 合併輸出效果
final TreeColumnMetadata columnGroup = metadata.append("資料集");
columnGroup.append(new BeanProperty("value1")).getContentFormat().setBorder(Border.NR);
columnGroup.append(new BeanProperty("value2")).getContentFormat().setBorder(Border.TB);
columnGroup.append(new BeanProperty("value3")).getContentFormat().setBorder(Border.NL);

```

##### 產出結果






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



