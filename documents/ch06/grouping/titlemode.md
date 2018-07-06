*
*
*
*
*

``` java
dataColumn : ColumnMetadata  /** 對應的資料欄或資料來源. */
showGroupingRow : boolean    /** 是否顯示統計列. */


groupHeaderFormat : CellFormat /** 統計名稱欄位格式，預設同該欄位的資料列格式. */




```


groupContentFormat : CellFormat
groupContentFormats : Map<ColumnMetadata, CellFormat>



   
    boolean showGroupingRow = true;

    
    CellFormat groupHeaderFormat;

    /** 統計資料欄位格式，預設同資料列資料欄位格式. */
    CellFormat groupContentFormat;
    
    
level : int
mergedRowsLevel : int
prefix : String

suffix : String
titleMode : TitleMode