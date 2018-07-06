## 基本表格輸出

### PDF：建立 TableIText

#### createTableWithRatio\(float widthPercentage, int numColumns\)

#### createTable \(int numColumns\)

等寬表格，共有 numColumns 欄，並指定表格寬度佔頁面比例 widthPercentage。

#### createTableWithRatio\(float widthPercentage, float\[\] widths\)

#### createTable \(float\[\] widths\)

指定欄寬比例widths，並指定表格寬度佔頁面比例 widthPercentage。

#### createTable\(LengthUnit unit, float... realWidths\)

指定度量單位，並直接指定各欄的實際寬度。若總計寬度超過頁面大小，則自動以滿版分配比例顯示。  
但用PaintTool公用類別輸出時，仍顯示為實際大小。處理特殊頁首頁尾時，有可能會使用此建構方式。

### PDF 表格設定

TableIText 繼承 PdfPTableWrapper，其中包含以下 delegate 函式可操作原生 PdfPTable，控制輸出位置及欄位呈現設定等等。

* setExtendLastRow\(boolean\)
* setFooterRows\(int\)
* setHeaderRows\(int\)
* setHorizontalAlignment\(DocumentAlign\)
* setHorizontalAlignment\(int\)
* setKeepTogether\(boolean\)
* setSkipFirstHeader\(boolean\)
* setSkipLastFooter\(boolean\)
* setSpacing\(double\)
* setSpacingAfter\(double\)
* setSpacingAfterInCM\(float\)
* setSpacingBefore\(double\)
* setSpacingBeforeInCM\(float\)
* setSplitLate\(boolean\)
* setSplitRows\(boolean\)

### 增加欄位內容. ADD CELL

TableItext 所提供的 addCell\(...\) 函式，其參數依型別分類，排列組合而成

* 內容 
  * Object text / CellDataSource source ： 二選一，欄位的內容文字。
  * SubPhrase... subPhrases ： varargs 必定在最後一項，用於同一欄位有多種文字格式時。
* 外觀
  * CellFormat cellFormat ：本欄樣式。
  * int colspan ： 跨欄位數。
  
```
addCell(Object , SubPhrase...)  
addCell(Object , CellFormat, int, SubPhrase...)  
addCell(Object , CellFormat, SubPhrase...)  
addCell(Object , int, SubPhrase...)  
addCell(SubPhrase...)  
addCell(CellFormat, int, SubPhrase...)  
addCell(CellFormat, SubPhrase...)  
addCell(int, SubPhrase...)  
addCell(CellDataSource, CellFormat, int, SubPhrase...)  
addCell(CellDataSource, CellFormat, SubPhrase...)  
addCell(CellDataSource, int, SubPhrase...)  
addCell(CellDataSource, SubPhrase...)  
addCell(PdfPCell) // ITEXT 原生METHOD
```

### Excel

* 取得 ExcelDocument<?, ?> document 。
* 呼叫 document.createSheet(name) 得到 ExcelSheet<?> 。
* 輸出CELL 位置皆以 ExcelPoint定位，其行列計數由 0 起算。
  * appendCell(ExcelPoint, Object)
  * appendCell(ExcelPoint, Object, CellFormat)
* 有兩個 ExcelPoint 輸入時，表示合併儲存格。
  * appendCell(ExcelPoint, ExcelPoint, Object, CellFormat)
* 插入 EXCEL 公式需用專用 METHOD 
  * appendFormulaCell(ExcelPoint, String, CellFormat)





















