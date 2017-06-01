# 文字內容

* **適用於 PDF**

本章主要針對 PDF 輸出文字內容進行說明，因為對 Excel 文件而言，所有內容皆為表格輸出。


## 基本輸出

writeText(…) 提供基本輸出文字功能，可設定字型大小及對齊方式。
預設的字型大小為 itext-config.properties 中的「default.font.size」，也可經由 pdfDocument.setFontSize() 重新設定。
預設行高為 1.25 倍字高。

``` java 
@Test
public void test_basicText() {
    super.createPDF(pdfDocument -> {
        pdfDocument.setFontSize(12); // set Default Size
        final String text = "中文，第二字面：「" + "𠀝" + "」(下/上)";
        pdfDocument.writeText(text + "Size:10", 10);
        pdfDocument.writeText(text + "Size:12 (Default)");
        pdfDocument.writeText(text + "Size:14", 14);
        pdfDocument.writeText(text + "Size:16", 16);
        pdfDocument.writeText(text + "Size:18", 18);
        pdfDocument.writeText(text, 10, DocumentAlign.RIGHT);
        pdfDocument.writeText(text, 10, DocumentAlign.CENTER);
    });
}
```



    