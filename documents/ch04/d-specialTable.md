
## 特殊樣式處理

* 對 PDF 的支援度較高。

### 欄位：CellType

``` JAVA
public interface Celltype {
    Celltype NONE = text -> text;
    String asText(String defaultText); // FOR EXCEL
}
```

專案若自行定義 Celltype ，同時必須實作對應的 CellCreator。

``` java 
public interface CellCreator<T extends Celltype> {
    PdfPCell create(CHTFontFactory fontFactory, int fontSize, String text, T cellType, Paragraph paragraph);
}
```


* 部分定義可合併使用

#### 如何自定義 CellType

 TODO



### TableiTextDecorator


實作以下 MEHTOD，當每一個單獨表格繪製完成後，可用自訂的TableiTextDecorator加上表格裝飾效果。
UDE-Report 也有提供一些常用的的裝飾類別。

```
    public abstract void drawTableLayout(PDFDocument pdfDocument //
            , PdfPTable table //
            , float[][] widths //
            , float[] heights //
            , int headerRows //
            , int rowStart//
            , PdfContentByte[] canvases);
```

