## 頁首、頁尾控制

頁首、頁尾同樣經由 BaseLayoutInfo 設定。  
但因 Excel的支援性問題，BaseLayoutInfo 只可設定基本的文字或頁面輸出。  
若用 LayoutInfo 輸出 PDF ，則有更多彈性的樣式可以選擇。

以下是設定頁首、頁尾時的兩個主要參數 ItemPosition 及 RepeatMode：

* BaseLayoutInfo.java

``` java
public class BaseLayoutInfo {
  /** 設定指定位置上的 HEADER. */
  void setHeader(ItemPosition position, Header header) {}
  /** 增加指定位置上的 HEADER. */
  void addHeader(ItemPosition position, Header header) {}
  /** 清除指定位置上的 HEADER. */
  void removeHeaders(ItemPosition position) {}
}
public interface Header {
    default RepeatMode getRepeatMode() { return RepeatMode.ALL; }
}
```

在設定 Header 時，必須指定 ItemPosition，也就是相同的 Header 可以被放置在文件上的任何位置。而 Header 介面本身要求回傳 RepeatMode ，由此決定該 HEADER 在哪些頁次會被輸出。getRepeatMode() 的預設回傳為 ALL，也就是在所有頁次都會出現。



### 定位點 (ItemPosition)

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
  
### 重複模式 (RepeatMode)

|Name| 說明|
|--|--|
|ALL|每頁出現|
|FIRST_PAGE    | 只在文件的第一頁輸出 (不論該頁是否計算頁數) |
|CONTENT_PAGES | 於頁次計數不為0的頁面輸出 (ONLY-FOR-PDF) |
|COVERAGE_PAGES| 於頁次計數為0的頁面輸出 (ONLY-FOR-PDF) |
|ODD_PAGES     | 於奇數頁次輸出 |
|EVEN_PAGES    | 於偶數頁次輸出 |


### 基本文字輸出

set/addTextHeader 可以輸出固定文字內容並且指定字體大小，上例即使用此方法設定頁首、頁尾。基本文字輸出也可以控制字體樣式，如下例。注意UNDERLINE樣式，應用在頁首時，可能會佔用 HeaderExtra 的空間 \(兩條虛線之間\)。

``` java
  addTextHeader(ItemPosition, RepeatMode, String, int)
  addTextHeader(ItemPosition, RepeatMode, String, int, FontStyle)
  addTextHeader(ItemPosition, String, int)
  addTextHeader(ItemPosition, String, int, FontStyle)
  setTextHeader(ItemPosition, RepeatMode, String, int)
  setTextHeader(ItemPosition, RepeatMode, String, int, FontStyle)
  setTextHeader(ItemPosition, String, int)
  setTextHeader(ItemPosition, String, int, FontStyle)
```


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


* **TODO : EXCEL 支援及範例**

### 基本頁碼輸出

set/addPagingHeader 可以輸出目前頁次，並指定字體大小。

PagingPattern

| 項目 | 說明 | 預設英文格式(PageHeaderEN) | 預設中文格式(PageHeaderZH) |
| --- | --- | --- | --- |
| PAGE | 文件頁次 | Page {p} | 第{p}頁 |
| TOTAL\_PAGES | 文件頁數 | Total pages:{tp} | 共{tp}頁 |
| BOTH | 文件頁次＋文件頁數 | Page {p} of {tp} | 第{p}頁，共{tp}頁 |
| SECTION | 目前節次 | {s} | 第{s}節 |
| PAGE\_IN\_SECTION | 目前節內頁次 | Page {sp} | 第{sp}頁 |
| SECTION\_AND\_PAGE | 目前節次＋目前節內頁次 | Page {s} - {sp} | 第{s}-{sp}頁 |
| SECTION\_PAGES | 各節總頁數 | Total pages:{tsp} | 共{tsp}頁 |

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

* **TODO : EXCEL 支援及範例**



## PDF

### 多文字格式

### 表格輸出

若有需要，可用 DocumentFormat 參數做判斷處理。

