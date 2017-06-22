### 巢狀表格

``` java
final NestTableMetadata metadata = new NestTableMetadata();
metadata.append("年度", new BeanProperty("text1"));
metadata.append("地區", new BeanProperty("text2"));
metadata.append("項目", new BeanProperty("text3"));
metadata.subTable(subTable -> {
    subTable.append("值1");
    subTable.append("值2");
    subTable.nextRow();
    subTable.append("值3");
    subTable.append("值4");
    subTable.append("值5");
    subTable.nextRow();
    subTable.append("值A");
    subTable.append("值B");
    subTable.append("值C");
    subTable.append("值D");
});
```

UDE-Report 所定義的巢狀表格，其巢狀新增方式是一塊塊插入子表格的做法。
與 TreeTableMetadata 相較，產出 PDF 的欄位 LAYOUT 會比較有彈性。如此例中，值1~5/A~C 欄位所佔寬度不同且未對齊；但生成EXCEL時，因試算表的天生限制，加上輸出原則以盡量不合併欄位為主，所以生成結果會與 PDF 文件有些許不同。




![](/assets/ch06/nestTable-basic.png)





竹

當一筆資料可能需要多列才足以輸出完整內容時，在此定義為巢狀表格，需要使用NestTableMetadata進行巢狀描述定義。以下用一個典型的表冊範例示範如何描述巢狀表格。







上例分別用同一個 TreeTableMetadata 轉換表格資料為 PDF / EXCEL 輸出。  
可以看到在 PDF 中，每一頁會重複顯示標題部分；而 Excel 則否。

