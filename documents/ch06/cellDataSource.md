

# CellDataSource


基本來源有三種：

* BeanProperty
  * 由 java bean 屬性取得內容。
  * 等效 dataSource(propName)。

* Const 
  * 定字資訊
  ``` java
    Const.of("XXXX")
    Const.of(BigDecimal.ONE)
  ```
  
* Counter 

如下例增加一個「年度」欄位，其資料值「new BeanProperty("text1")」表示

