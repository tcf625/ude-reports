### 欄位插入模式

有時，為配合程式運算邏輯，無法依據由前到後的順序插入欄位。
像是輸出於前方的合計欄位、欄位定義在其它形式的資料結構中。
TreeTableMetadata 對應的 TreeColumnMetadata 提供更多模式的插入欄位方法 (參考JQuery命名)。

#### After and Before

``` java
final TreeColumnMetadata columnValue1 = metadata.append("值1", new BeanProperty("value1"));
final TreeColumnMetadata columnValue2 = metadata.append("值2", new BeanProperty("value2"));
columnValue1.after("值1前面");
columnValue1.before("值1後面");
```


#### ColumnGroup : append / pAfter and Before



