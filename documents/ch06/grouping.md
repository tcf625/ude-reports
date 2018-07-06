## 資料群集 & 統計

* 原則上建議用在 TreeTableMetadata

### 範例

![](/assets/ch06/table-grouping-sample1.png)

* 在上例中，黃底部分是原始資料。
* 群集定義有三層：
  * Level0 : 總計 (桃色)
  * Level1 : 年度 小計 (藍色)
  * Level2 : 地區 小計 (綠色)
* 原始資料必須 order by 年度、地區。
  
* 群組列可呈現於資料上方或下方或兩者皆有。  
 

addGroupLevel\(CellDataSource\)  
addGroupLevel\(ColumnMetadata\)

addGroupLevel\(String titleSuffix, CellDataSource\)  
addGroupLevel\(String titleSuffix, ColumnMetadata\)







