
# 表格 (Table)

* **適用於 PDF / EXCEL**

## CellDataSource


## CellFormat

本套件以 CellFormat 來定義個別欄位l的顯示外觀，此物件可適用於 PDF 及 Excel 表格，唯部分性質可能在兩種文件格式中的支援度不同。於_'表格描述定義'_中，同樣使用 CellFormat 設定格式外觀。「表格欄位樣式」一節會再詳細說明。

## 表格描述定義

後面章節會介面 UDE-Report 的「表格描述定義」，可以針對 JavaBean / Map 形式資料來源，以相同的Table Metadata轉換為不同格式(PDF/Excel/CSV)輸出，方便客戶要求變換報表格式時處理。

本章介紹製表所用的基礎API，理論上所有的表格皆可以用基礎繪製，但是可使用「表格描述定義」時，還是建議盡量使用，以簡化程式開發。

基礎API 適用的應用情境包含格式複雜的申請書、跨頁的大型表格、格式簡單的名條、頁首頁尾特殊排版。

## PDF

本套件主要使用 iText 中功能較完整的 PdfPTable 做為底層表格輸出元件。但使用時，應使用 Ude-Report 所包裝的 TableiText 類別操作。

TableiText 是 PdfPTable 的包覆類別，它提供中文處理能力、樣式簡易設定、跨欄、跨列與InnerTable相關操作，也可取得原生 PdfPTable 物件進行更複雜的操作。

TableiText 可使用PDFDocument 的相關函式建立，當所有欄位加入表格後，再呼叫 appendMe() 進行輸出。
如欲輸出於頁面上的指定位置，也可以使用「定位輸出」的相關工具類別函式處理，但是表樣的呈現彈性會較差。

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



