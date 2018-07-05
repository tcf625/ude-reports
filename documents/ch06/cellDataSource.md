

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
  * 計數器，在同一個 TRANSFER 中有效。
  * 重新轉換時會回到初始值。


