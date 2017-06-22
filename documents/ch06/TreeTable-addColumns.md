### 欄位插入模式

有時，為配合程式運算邏輯，無法依據由前到後的順序插入欄位。
像是輸出於前方的合計欄位、欄位定義在其它形式的資料結構中。
TreeTableMetadata 對應的 TreeColumnMetadata 提供更多模式的插入欄位方法 (參考JQuery命名)。

#### After and Before

``` java
// !
final TreeColumnMetadata columnValue = metadata.append("值1", new BeanProperty("value1"));
metadata.append("值2", new BeanProperty("value2"));
columnValue.before("值1前面");
columnValue.after("值1後面");

// !
final TreeColumnMetadata columnGroup = metadata.append("資料集");
columnGroup.append("值3", new BeanProperty("value3"));
columnGroup.append("值4", new BeanProperty("value4"));
columnGroup.before("資料集前面");
columnGroup.after("資料集後面");
```

#### 產出結果(After and Before)



#### ColumnGroup : appendAt /  prepend


``` java
final TreeColumnMetadata columnGroup = metadata.append("資料集");
columnGroup.append("值1", new BeanProperty("value1"));
columnGroup.append("值2", new BeanProperty("value2"));
columnGroup.prepend("資料集第1項");
columnGroup.appendAt("資料集第3項", 2);
metadata.append("值3");
```


#### 產出結果(ColumnGroup )