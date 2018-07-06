## 資料群集 & 統計

* 原則上建議用在 TreeTableMetadata

### 範例

![](/assets/ch06/table-grouping-sample1.png)

* 在上例中，黃底部分是原始資料。
* 群組列可呈現於資料上方或下方或兩者皆有。  
  ``` java
final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總計", Position.BEFORE);
final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總計", Position.AFTER);
final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總計", Position.BOTH);
  ```
  
* 群集(GroupingLevel)定義有三層：
  * Level0 : 總計 (桃色)
  * Level1 : 年度 小計 (藍色)
  * Level2 : 地區 小計 (綠色)
* 原始資料必須 order by 年度、地區。

* 目前有設 mergedRows時，最後一層有顯示群組列的層級必設，不然會破壞跨欄。




addGroupLevel\(CellDataSource\)  
addGroupLevel\(ColumnMetadata\)

addGroupLevel\(String titleSuffix, CellDataSource\)  
addGroupLevel\(String titleSuffix, ColumnMetadata\)





> ## NOTE 
>
> 戶役政系統 所實作的 GroupingKey
>

