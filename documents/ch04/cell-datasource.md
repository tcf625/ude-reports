
# CellDataSource

用在 TableIText 時，需配合 table.setDataObject(...)

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

* 格式或型別轉換。
* 一般轉換器，皆會提供建立轉換器的Function，供 dataSource(...) 函式使用。
```java
CellDataSource dataSource(BiFunction<CellDataSource, CellDataSource, ? extends CellDataSource>, String, String)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, CellDataSource)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, String)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, CellDataSource...)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, String...)
```

### 常見轉換器

* Code : 代碼檔對應文字
* ToText : 文字格式化
* Mask : 文字遮罩
* Abbreviate : 刪節文字
* Decoder : 自訂文字對應
* TextGrid : 如原始資料為陣列或LIST，將其合併輸出。

## 欄公式 (HorizontalExpression)

column 間有相依關係，但**JavaBean未實作相關計算**時，可以使用公式相關設定。
早期發展時有字串相加、日期處理之類的公式，但目前版本暫時不再提供。

* 目前比較穩定被使用的，只有數值間的計算公式(NumberOP)。  

* 專案也可以客制化公式，如上述提到的日期計算或中位數、平均數計算等等。

### NumberOP

數值間的計算公式。
基本開頭，應是一個 NumberValue 物件，它會把指定資料來源試轉為 BigDecimal，以進行後續計算。
``` java
NumberValue(Number)
NumberValue(ColumnMetadata) // 優先於 CellDataSource
NumberValue(CellDataSource)
NumberValue(HorizontalExpression<BigDecimal>)
```
建立第一個項目以後，就可以呼叫串接 API，構建完整運算式。
```
NumberOP add(CellDataSource) // = plus
NumberOP add(ColumnMetadata) // = plus
NumberOP add(Number) // = plus
NumberOP dividedBy(CellDataSource)
NumberOP dividedBy(CellDataSource, int)
NumberOP dividedBy(ColumnMetadata)
NumberOP dividedBy(ColumnMetadata, int)
NumberOP dividedBy(Number)
NumberOP dividedBy(Number, int)
NumberOP minus(CellDataSource)
NumberOP minus(ColumnMetadata)
NumberOP minus(Number)
NumberOP multiply(CellDataSource)
NumberOP multiply(ColumnMetadata)
NumberOP multiply(Number)
NumberOP plus(CellDataSource)
NumberOP plus(ColumnMetadata)
NumberOP plus(Number)
```

### 

## Notes

輸出 < li \> 效果文字，配合 NoteItem 使用。

* 可設定不同層級的索引文字樣式。(如 : 1. (1) 一、)
* 可設定不同層級的文字格式。
 


