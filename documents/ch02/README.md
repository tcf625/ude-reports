# 概述

因為各種輸出類型的天生限制，各底層API對頁面控制的處理支援度自然有所不同。「文件」類型，是對於頁面控制支援度最齊全的格式。

* PDFDocument

``` java
setPageSize(Rectangle)      // 頁面大小與方向
setLayoutInfo(LayoutInfo)   // 邊界留白與頁首頁尾
setMarkers(List<? extends MarkInfo>) // 浮水印相關定義
```

* ExcelDocument - ExcelSheet

  在EXCEL中，頁面控制原則上以 sheet 為單位設定。目前 ude-reports 對於 EXCEL 版面控制沒有做太多的包裝處理，有需要的話，可以透過 getRealSheet() 取得 poi 原生物件進行設定。

``` java
setPrintPageSize(PrintPageSize)
```

## 共通介面

當同一種報表內容要用不同格式輸出時，通常會用一個共同的 AbstractReport 實作 DocumentGenerator 及其子介面，其中與頁面控制相關的項目有以下幾項：

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

目前有 PDF / Excel 支援，PDF 格式的頁面定義較彈性；EXCEL則受限於 POI 的 void setPaperSize(short size); 只有固定的選項可用。

``` java
/* Sample_PageSize */

@Test
public void test_PDF_A3() throws IOException {
    super.createPDF(pdfDocument -> {
        pdfDocument.setPageSize(PageSize.A3);
        pdfDocument.writeText("A3");
    });
}
@Test
public void test_Excel_A3() {
    super.createExcel(document -> {
        final ExcelSheet<?> sheet = document.createSheet("sheet_a3");
        sheet.setPrintPageSize(PoiDefaultSize.A3);
    });
}

```



## BaseLayoutInfo 邊界與頁首頁尾控制

見後文說明。

## 分節 getSections()

UDE-Report 可利用 Sections 機制，組合多個 DocumentGenerator 輸出為同一檔案，
見後文說明.













