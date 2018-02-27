## 頁首、頁尾控制

頁首、頁尾同樣經由 BaseLayoutInfo 設定。  
但因 Excel的支援性問題，BaseLayoutInfo 只可設定基本的文字或頁面輸出。  
若用 LayoutInfo 輸出 PDF ，則有更多彈性的樣式可以選擇。

### 定位點

輸出位置由position指定，依左中右／上下組合，共有六個位置：

* 文字輸出於左邊時置左；右邊時置右；中間時置中。
* 輸出在上方時，會靠下對齊marginTop定位點。
* 輸出在下方時，會靠上對齊marginBottom定位點。

若預留空間不足，則輸出內容會超出頁首、尾範圍。

* Sample\_PageHeader\_BasicText.java :: test\_BASELINE

  ```java
  final LayoutInfo layoutInfo = new LayoutInfo();
  layoutInfo.setTextHeader(ItemPosition.LeftHeader, text, 14);
  layoutInfo.setTextHeader(ItemPosition.CenterHeader, text, 12);
  layoutInfo.setTextHeader(ItemPosition.RightHeader, text, 8);
  layoutInfo.setTextHeader(ItemPosition.LeftFooter, text, 8);
  layoutInfo.setTextHeader(ItemPosition.CenterFooter, text, 12);
  layoutInfo.setTextHeader(ItemPosition.RightFooter, text, 14);
  pdfDocument.setLayoutInfo(layoutInfo);
  ```

  ![](/assets/ch02/header_position.png)

* ### 基本文字輸出

set/addTextHeader 可以輸出固定文字內容並且指定字體大小，上例即使用此方法設定頁首、頁尾。基本文字輸出也可以控制字體樣式，如下例。注意UNDERLINE樣式，應用在頁首時，可能會佔用 HeaderExtra 的空間 \(兩條虛線之間\)。

* Sample\_PageHeader\_BasicText.java :: test\_fontStyle
  ```java
  final LayoutInfo layoutInfo = new LayoutInfo();
  layoutInfo.setTextHeader(ItemPosition.LeftHeader, String.format(text, "BOLD"), 12, FontStyle.BOLD);
  layoutInfo.setTextHeader(ItemPosition.CenterHeader, String.format(text, "ITALIC"), 12, FontStyle.ITALIC);
  layoutInfo.setTextHeader(ItemPosition.RightHeader, String.format(text, "UNDERLINE"), 12, FontStyle.UNDERLINE);
  layoutInfo.setTextHeader(ItemPosition.LeftFooter, String.format(text, "STRIKETHRU"), 12, FontStyle.STRIKETHRU);
  layoutInfo.setTextHeader(ItemPosition.CenterFooter, String.format(text, "BOLDITALIC"), 12, FontStyle.BOLDITALIC);
  pdfDocument.setLayoutInfo(layoutInfo);
  ```

  ![](/assets/ch02/header_text_fontStyle.png)

### 基本頁碼輸出

| 項目 | 說明 | 預設中文格式 | 預設英文格式 |
| --- | --- | --- | --- |
| PAGE  |  文件頁次| Page  | |
| TOTAL\_PAGES |   文件頁數| | |
| BOTH   | 文件頁次＋文件頁數| | |
| SECTION  |  目前節次| | |
| PAGE\_IN\_SECTION  |  目前節內頁次| | |
| SECTION\_AND\_PAGE  |  目前節次＋目前節內頁次| | |
| SECTION\_PAGES  |  各節總頁數| | |


```java
final PDFSampleContent setting = pdfDocument -> {
    pdfDocument.setPageSize(PageSize.A5.rotate());
    final LayoutInfo layoutInfo = new LayoutInfo();
    layoutInfo.setHeader(ItemPosition.LeftHeader, PageHeaderEN.PAGE, 14);
    layoutInfo.setHeader(ItemPosition.CenterHeader, PageHeaderEN.BOTH, 14);
    layoutInfo.setHeader(ItemPosition.RightHeader, PageHeaderEN.TOTAL_PAGES, 14);
    layoutInfo.setHeader(ItemPosition.LeftFooter, PageHeaderZH.PAGE, 14);
    layoutInfo.setHeader(ItemPosition.CenterFooter, PageHeaderZH.BOTH, 14);
    layoutInfo.setHeader(ItemPosition.RightFooter, PageHeaderZH.TOTAL_PAGES, 14);
    pdfDocument.setLayoutInfo(layoutInfo);
};
super.createPDF(setting.andThen(this::outputRepeatText));
```

## PDF

### 多文字格式

### 表格輸出

若有需要，可用 DocumentFormat 參數做判斷處理。

