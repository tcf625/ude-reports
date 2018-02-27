## 頁首、頁尾控制

頁首、頁尾同樣經由 BaseLayoutInfo 設定。  
但因 Excel的支援性問題，BaseLayoutInfo 只可設定基本的文字或頁面輸出。  
若用 LayoutInfo 輸出 PDF ，則有更多彈性的樣式可以選擇。

以下是設定頁首、頁尾時的兩個主要參數 ItemPosition 及 RepeatMode：

* BaseLayoutInfo.java

```java
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

在設定 Header 時，必須指定 ItemPosition，也就是相同的 Header 可以被放置在文件上的任何位置。而 Header 介面本身要求回傳 RepeatMode ，由此決定該 HEADER 在哪些頁次會被輸出。getRepeatMode\(\) 的預設回傳為 ALL，也就是在所有頁次都會出現。

### 定位點 \(ItemPosition\)

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

### 重複模式 \(RepeatMode\)

| Name | 說明 |
| --- | --- |
| ALL | 每頁出現 |
| FIRST\_PAGE | 只在文件的第一頁輸出 \(不論該頁是否計算頁數\) |
| CONTENT\_PAGES | 於頁次計數不為0的頁面輸出 \(ONLY-FOR-PDF\) |
| COVERAGE\_PAGES | 於頁次計數為0的頁面輸出 \(ONLY-FOR-PDF\) |
| ODD\_PAGES | 於奇數頁次輸出 |
| EVEN\_PAGES | 於偶數頁次輸出 |






