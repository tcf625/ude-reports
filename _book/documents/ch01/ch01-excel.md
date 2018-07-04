## 概述

* 如 Excel

* Grid-Table

* 頁首、頁尾樣式受限


## Excel


負責產製Excel的類別是ExcelDocument，使用createXLS／XLSX可分別得到用於 Excel 97~2003或Excel2007版本的對應物件。
製表時，由 ExcelDocument createSheet() 得到ExcelSheet，然後利用appendCell() / appendFormulaCell() 加入欄位。最後呼叫ExcelDocument.close() 即可完成檔案輸出。



## ODS

**_尚未支援。_**