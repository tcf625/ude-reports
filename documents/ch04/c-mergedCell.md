## 表格合併欄位

### PDF： addCell (..., int colspans, ...)

### PDF： innerTable (...)

    垂直合併時，針對其它非合併部分，應使用 innerTable 
    
    因為：iText 原生API的 rowSpan 在跨頁時的呈現不易控制。

### Excel： appendCell(ExcelPoint, ExcelPoint, Object, CellFormat) 
