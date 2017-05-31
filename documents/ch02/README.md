# 概述

因為各種輸出類型的天生限制，各底層API對頁面控制的處理支援度自然有所不同。「文件」類型，是對於頁面控制支援度最齊全的格式。

* PDFDocument

``` JAVA
setPageSize(Rectangle)
setLayoutInfo(LayoutInfo)
setMarkers(List<? extends MarkInfo>)
```

* ExcelDocument - ExcelSheet

``` JAVA
setPrintPageSize(PrintPageSize)
```











各輸出的




在 DocumentGenerator 介面中，與頁面控制相關的項目有以下幾項
。AbstractPDFGenerator 是 DocumentGenerator 的主要實作。

``` java
default public PrintPageSize getPrintPageSize() {
    return PrintPageSize.DEFAULT;
}
default BaseLayoutInfo prepareLayoutInfo() {
    return null;
}
default List<DocumentGenerator> getSections() {
    return Arrays.asList(this);
};
```

## PrintPageSize 頁面大小

目前有 PDF / Excel 



本章主要以

