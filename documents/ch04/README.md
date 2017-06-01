
# 表格


## PDF

本套件主要使用iText中的PdfPTable做為底層表格輸出元件。但使用時，應使用com.iisigroup.ude.report.itext2.table.TableiText。
TableiText 是 PdfPTable的包覆類別，它提供中文處理能力、樣式簡易設定、跨欄、跨列與InnerTable相關操作，也可取得原生 PdfPTable 物件進行更複雜的操作。
TableiText於製表常用的諸多特性都有進行包裝，後續會在表格輸出的相關章節中有詳細說明。但本套件另有提供通用表格特性，可使用通用表格的時候，還是建議盡量使用通用表格，以簡化程式開發。TableiText適用的應用情境包含格式複雜的申請書、跨頁的大型表格、格式簡單的名條、頁首頁尾特殊排版。

要建立TableiText，應使用PDFDocument 的相關函式，當所有欄位加入完成後，呼叫它的appendMe()進行輸出。如欲輸出於頁面上的指定位置，則是使用PaintTool.drawTable(…) method。

## Excel

ExcelSheet 類別已包裝常用的 POI Excel 輸出的相關功能。

``` java
appendCell(ExcelPoint, ExcelPoint, Object, CellFormat)
appendCell(ExcelPoint, Object, CellFormat)
appendCell(Point, Object, CellFormat)
appendCell(Point, Point, Object, CellFormat)
appendFormulaCell(ExcelPoint, String, CellFormat)
setPrintPageSize(PrintPageSize)
setBorder(ExcelPoint, ExcelPoint, Border)
setColumnWidth(int, float)
setColumnWidthInPixel(int, float)
setRowHeight(int, float)
```



