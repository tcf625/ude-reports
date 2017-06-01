# 文字內容

* **適用於 PDF**

本章主要針對 PDF 輸出文字內容進行說明，因為對 Excel 文件而言，所有內容皆為表格輸出。

## 基本輸出

writeText\(…\) 提供基本輸出文字功能，可設定字型大小及對齊方式。  
預設的字型大小為 itext-config.properties 中的「default.font.size」，也可經由 pdfDocument.setFontSize\(\) 重新設定。基本輸出的預設行高為 1.25 倍字高。
因為預設使用新細明體，所以可以顯示第二字面文字(字型檔:mingliub.ttc)。

```java
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

![](/assets/ch03/basicText.png)

### 預設字型調整

文件預設字型可使用setFontInfo(…)調整。
首先取得指定字體的CHTFontFactory，用以建立想要產出的字型資訊（FontInfo），可設定大小、Style、顏色、底色。但一般不會頻於設定文件預設字型，而會採用下一個

``` java
final String text = "中文，第二字面：「" + "𠀝" + "」(下/上)";
final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
pdfDocument.setFontInfo(kaiFactory.createFontInfo(10, Color.BLUE));
pdfDocument.writeText("轉成標楷體/藍色字，無第二字面可供顯示。");
pdfDocument.writeText(text + "Size:18", 18);
```
![](/assets/ch03/changeFont.png)

由上例可看出，若使用含非0字面字型檔的字型，則本套件支援非0字面的中文輸出，一般直接使用iText輸出的範例程式是未做此項處理的。但若使用的字型不包含非0字面字型檔，則非0字面文字自然也無法顯示，同時不會佔用輸出空間。