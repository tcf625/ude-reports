## 特殊樣式處理

* 對 PDF 的支援度較高。

### CellType

``` JAVA
public interface Celltype {
    Celltype NONE = text -> text;
    String asText(String defaultText); // FOR EXCEL
}
```

專案若自行定義 Celltype ，同時必須實作對應的 CellCreator

``` java 
public interface CellCreator<T extends Celltype> {
    PdfPCell create(CHTFontFactory fontFactory, int fontSize, String text, T cellType, Paragraph paragraph);
}
```





### TableiTextDecorator