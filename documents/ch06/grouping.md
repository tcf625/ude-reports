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
  
* 群集階層(GroupingLevel)定義有三層：
  個別群組列不顯示，可用 setShowGroupingRow 設定。
  * Level0 : 總計 (桃色)
  * Level1 : 年度 小計 (藍色)
  * Level2 : 地區 小計 (綠色)
  
  ``` java
  addGroupLevel\(ColumnMetadata\)  // 建議優先使用。
  addGroupLevel\(CellDataSource\)  // 若欄位不存在表格中
  ```
  
* 原始資料必須 order by 年度、地區。

* 目前有設 mergedRows時，最後一層有顯示群組列的層級必設，不然會破壞跨欄。

* 對原始資料中的值，沒有其它描述定義其中的從屬關係。

> **NOTE**
>
> 戶役政系統 所實作的 GroupingKey 是針對同一欄位的資料，也有層級關係時。
> 如 台北市->各區
>    新北市->各區
>    ...
>    台灣省->各縣市->各郷鎮市區
>
> 因為其資料的群集、加總模式比較複雜，所以另行定義 GroupingKey...等相關資料結構，先行針對資料進行運算。             
> 而非交由 TableTranfer 中的 GroupingLevel 處理。
>
> 需要做分頁 