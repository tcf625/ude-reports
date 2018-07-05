

# CellDataSource


## 基本來源

* BeanProperty
  * 由 java bean 屬性取得內容。
  * 等效 dataSource(propName)。
  

* Const 
  * 定字資訊
  * 當下日期物件
   ``` java
    Const.of("XXXX");
    Const.of(BigDecimal.ONE);
    DateSource.now();
```
    
* Counter 
  * 計數器，在同一個 TRANSFER 中有效。
  * 重新轉換時會回到初始值。


* DataObject
  * 原始資料本身 (少用)
  
  ``` java 
  DataObject.SELF
 ```

## 轉換器

  格式或型別轉換。

## 欄公式

  column 間有相依關係，但**JavaBean未實作相關計算**時，可以使用公式相關設定。
  


