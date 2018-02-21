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


## 頁面大小

目前 PDF / Excel 皆支援設定頁面大小。唯PDF 格式的頁面定義較彈性，EXCEL 受限於 POI API，只有固定的選項可用。

``` java
/* Sample_PageSize */
@Test
public void test_PDF_A3() throws IOException {
    super.createPDF(pdfDocument -> {
        pdfDocument.setupPageSize(PageSize.A3);
        pdfDocument.writeText("A3");
    });
}
@Test
public void test_Excel_A3() {
    super.createExcel(excelDocument -> {
        final ExcelSheet<?> sheet = excelDocument.createSheet("sheet_a3");
        sheet.setPrintPageSize(PoiDefaultSize.A3);
    });
}
```


## 共通介面

當同一種報表內容要用不同格式輸出時，通常會用共同的 AbstractReport 實作 DocumentGenerator 的任一介面，如 PDFGenerator、ExcelGenerator…。

### MultiFormatReportSupport

當文件產出類別同時提供多種格式選擇時，可加入實作 MultiFormatReportSupport，它依據以下兩個函式。


``` java
default String toExcelSheetName() {
    return "sheet";
};
default PrintPageSize toPrintPageSize() {
    final Rectangle pdfPageSize = getPageSize();
    return HELPER.lookupPringPageSize(pdfPageSize);
};
```

``` java
    public GSS0010() {
        super(AllReports.GSS0010, PageSize.A3);
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        pdfDocument.writeText("TEST-GSS0010");
    }

    @Override
    public void generateExcelContent(final ExcelDocument<?, ?> document) {
        final ExcelSheet<?> sheet = super.createExcelSheet(document);
        sheet.appendCell(new ExcelPoint(0, 0), "TEST-GSS0010", new CellFormat(Border.BOX));
        sheet.setColumnWidth(0, 20);
    }
```

## BaseLayoutInfo 邊界與頁首頁尾控制

見後文說明。

## 分節 getSections()

UDE-Report 可利用 Sections 機制，組合多個 DocumentGenerator 輸出為同一檔案，
見後文說明.


其中與頁面控制相關的項目有以下幾項：

``` java
default BaseLayoutInfo prepareLayoutInfo() {
return null;
}
default List<DocumentGenerator> getSections() {
return Arrays.asList(this);
};
```










