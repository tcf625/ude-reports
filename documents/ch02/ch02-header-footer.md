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


### 基本文字輸出

於頁首、頁尾輸出固定文字text並指定字體大小，輸出位置以邊界定位點自動計算。
在範例中，將以全黑的方框字元，呈現輸出於不同位置的對齊結果。



### 基本頁碼輸出


* PAGE	文件頁次
* TOTAL_PAGES	文件頁數
* BOTH 	文件頁次＋文件頁數
* SECTION	目前節次
* PAGE_IN_SECTION	目前節內頁次
* SECTION_AND_PAGE	目前節次＋目前節內頁次
* SECTION_PAGES	各節總頁數

``` java
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


  