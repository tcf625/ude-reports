##  基本表格輸出


### PDF 建立 TableIText


* createTable(float widthPercentage, float[] widths)
* createTable(float widthPercentage, int numColumns)
* createTable(LengthUnit unit, float[] realWidths)
指定度量單位，並直接指定各欄的實際寬度。若總計寬度超過頁面大小，則自動以滿版分配比例顯示。
但用PaintTool公用類別輸出時，仍顯示為實際大小。處理特殊頁首頁尾時，有可能會使用此建構方式。





1、	createTable(float  widthPercentage, int  numColumns)
等寬表格，共numColumns欄，並指定表格寬度佔頁面比例widthPercentage。

2、	createTable(float  widthPercentage, float[]  widths)
指定欄寬比例widths，並指定表格寬度佔頁面比例widthPercentage。

 
3、	createTable(Coordinate  c, float[]  realWidths)




4、	createTable(Coordinate  c, float  totalWidth, float[]  widths)
指定度量單位c、表格總寬度totalWidth，並指定欄寬比例widths。


### Excel 


excel 