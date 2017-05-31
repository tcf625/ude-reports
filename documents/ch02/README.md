# 概述

因為各種輸出類型的天生限制，各底層API對頁面控制的處理支援度自然有所不同。「文件」類型，是對於頁面控制支援度最齊全的格式。

* PDFDocument

``` JAVA
setPageSize(Rectangle)      // 頁面大小與方向
setLayoutInfo(LayoutInfo)   // 邊界留白與頁首頁尾
setMarkers(List<? extends MarkInfo>) // 浮水印相關定義
```

* ExcelDocument - ExcelSheet

  在EXCEL中，頁面控制原則上以 sheet 為單位設定。目前 ude-reports 對於 EXCEL 版面控制沒有做太多的包裝處理，有需要的話，可以透過 getRealSheet() 取得 poi 原生物件進行設定。

``` JAVA
setPrintPageSize(PrintPageSize)
```

## 共通介面


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

