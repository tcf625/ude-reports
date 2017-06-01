## 表格欄位樣式


本套件以CellFormat來定義個別Cell的顯示外觀。
ExcelDocument 及 TableiText 可用getDefaultFormat\(\)取得並設定適用整個表格的預設外觀定義。
個別Cell則是在addCell時，傳入指定的CellFormat物件即可。


一般建議在輸出任何表格內容前，就設定 defaultFormat 完成。
若輸出欄位後，才異動 defaultFormat，雖不會影響到已輸出的欄位外觀，但維護性可能較差。

  
當表格中含有InnerTable時，InnerTable的defaultFormat的未定義部分，會參考外表格的defaultFormat值。  

以下逐一說明 CellFormat 的設定內容。

### 底色

* setBackgroundColor(Color)

### 邊框

* setBorder(Border)
* setBorderWidth(Float)

### 對齊

* setAlignH(AlignH)
* setAlignV(AlignV)

### 字型、輸出

* setFontBold(Boolean)
* setFontSize(Integer)
* setFontType(FontType)
* setCellType(Celltype)
* setTextFormat(String)

### 大小

* setMinHeightInCM(Float)

### 留白

* setPadding(double)
* setPaddingB(double)
* setPaddingH(double)
* setPaddingL(double)
* setPaddingR(double)
* setPaddingT(double)
* setPaddingV(double)



