### GroupingLevel 的一些屬性說明。

``` java
dataColumn : ColumnMetadata  /** 對應的資料欄或資料來源. */
showGroupingRow : boolean    /** 是否顯示統計列. */

prefix : String              /** 統計列文字前綴. */
suffix : String              /** 統計列文字後綴. */
titleMode : TitleMode        /** 統計列文字內容 : (當下層級)(包含上層級資料)(無). */
groupHeaderFormat : CellFormat /** 統計名稱欄位格式，預設同該欄位的資料列格式. */
groupContentFormats : Map<ColumnMetadata, CellFormat>
                               /** 統計資料欄位格式，預設同該欄位的資料列格式. */

mergedRowsLevel : int  // 欄位合併模式設定

```
  


