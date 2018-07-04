### 基本文字輸出

set/addTextHeader 可以輸出固定文字內容並且指定字體大小，上例即使用此方法設定頁首、頁尾。基本文字輸出也可以控制字體樣式，如下例。注意UNDERLINE樣式，應用在頁首時，可能會佔用 HeaderExtra 的空間 \(兩條虛線之間\)。

```java
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





