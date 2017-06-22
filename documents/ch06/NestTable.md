### 巢狀表格

當一筆資料可能需要多列才足以輸出完整內容時，在此定義為巢狀表格，需要使用NestTableMetadata進行巢狀描述定義。以下用一個典型的表冊範例示範如何描述巢狀表格。



![](/assets/ch06/nestTable-sample.png)

上例分別用同一個 TreeTableMetadata 轉換表格資料為 PDF / EXCEL 輸出。  
可以看到在 PDF 中，每一頁會重複顯示標題部分；而 Excel 則否。

