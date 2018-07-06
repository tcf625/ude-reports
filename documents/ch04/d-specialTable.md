
## 特殊樣式處理

* 對 PDF 的支援度較高。

### 欄位：CellType

``` JAVA
public interface CellType {
    CellType NONE = new CellType() {    };
    default void setupContent(final ColumnMetadata c) { // For Consumer 串接
        c.getContentFormat().setCellType(this);
    }
    default void setupHeader(final ColumnMetadata c) { // For Consumer 串接
        c.getHeaderFormat().setCellType(this);
    }
}
```


* 部分定義可合併使用

#### 如何自定義 CellType

* TODO

專案若自行定義 Celltype ，同時必須實作對應的 CellCreator。

``` java 
public interface CellCreator<T extends Celltype> {
    PdfPCell create(CHTFontFactory fontFactory, int fontSize, String text, T cellType, Paragraph paragraph);
}
```


### 表格：TableiTextDecorator

自訂的TableiTextDecorator實作以下 MEHTOD，當每個單獨表格繪製完成後，可加上裝飾效果。

``` java
  public abstract void drawTableLayout(PDFDocument pdfDocument ... ) 
```

#### 常用裝飾類別(UDE內建)

* TableBorder 外框處理
* RowBackgroundColor 每列底色變化



