### 基本頁碼輸出

set/addPagingHeader 可以輸出目前頁次，並指定字體大小。

主要參數為 PagingPattern，以 PagingItem 定義使用的頁次資訊；getPrefix\(\)、getConjunction\(\)、getSuffix\(\) 定義前後綴及連接文字。  
若要更客制化的輸出多種頁次資訊，目前只支援 PDF 格式，需繼承 AbstractPagingHeader 實作。

| PagingItem | 說明 | 預設英文格式\(PageHeaderEN\) | 預設中文格式\(PageHeaderZH\) |
| --- | --- | --- | --- |
| PAGE | 文件頁次 | Page {p} | 第{p}頁 |
| TOTAL\_PAGES | 文件頁數 | Total pages:{tp} | 共{tp}頁 |
| BOTH | 文件頁次＋文件頁數 | Page {p} of {tp} | 第{p}頁，共{tp}頁 |
| SECTION | 目前節次 | {s} | 第{s}節 |
| PAGE\_IN\_SECTION | 目前節內頁次 | Page {sp} | 第{sp}頁 |
| SECTION\_AND\_PAGE | 目前節次＋目前節內頁次 | Page {s} - {sp} | 第{s}-{sp}頁 |
| SECTION\_PAGES | 各節總頁數 | Total pages:{tsp} | 共{tsp}頁 |

* Sample\_PageHeader\_BasicPage :: test\_BasicPaging

  ```java
  final PDFSampleContent setting = pdfDocument -> {
    pdfDocument.setPageSize(PageSize.A5.rotate());
    final LayoutInfo layoutInfo = new LayoutInfo();
    layoutInfo.setPagingHeader(ItemPosition.LeftHeader, PagingHeaderEN.PAGE, 14);
    layoutInfo.setPagingHeader(ItemPosition.CenterHeader, PagingHeaderEN.BOTH, 14);
    layoutInfo.setPagingHeader(ItemPosition.RightHeader, PagingHeaderEN.TOTAL_PAGES, 14);
    layoutInfo.setPagingHeader(ItemPosition.LeftFooter, PagingHeaderZH.PAGE, 10);
    layoutInfo.setPagingHeader(ItemPosition.CenterFooter, PagingHeaderZH.BOTH, 10);
    layoutInfo.setPagingHeader(ItemPosition.RightFooter, PagingHeaderZH.TOTAL_PAGES, 10);
    pdfDocument.setLayoutInfo(layoutInfo);
  };
  super.createPDF(setting.andThen(this::outputRepeatText));
  ```

  ![](/assets/ch02/header_paging_basic.png)

於不同頁次輸出、分節資訊及基本自訂格式可以參考下例

* Sample\_PageHeader\_BasicPage :: test\_Sections

```java
layout.setPagingHeader(ItemPosition.LeftHeader, RepeatMode.ODD_PAGES, PagingHeaderEN.SECTION_AND_PAGE, 10);
layout.setPagingHeader(ItemPosition.RightHeader, RepeatMode.EVEN_PAGES, PagingHeaderEN.SECTION_AND_PAGE, 10);

// 自訂格式文字
layout.setPagingHeader(ItemPosition.CenterHeader, PagingItem.BOTH_IN_SECTION.conjunction(" of "), 10);
layout.setPagingHeader(ItemPosition.LeftFooter, PagingHeaderEN.TOTAL_PAGES, 8);
layout.setPagingHeader(ItemPosition.CenterFooter, PagingItem.SECTION.prefix("Sec. "), 10);
layout.setPagingHeader(ItemPosition.RightFooter, PagingItem.SECTION_PAGES.prefix("Section Pages:"), 8);
```

![](/assets/ch02/header_paging_basic_sections.png)

* **TODO : EXCEL 支援及範例**



