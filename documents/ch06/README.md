
# 表格轉換架構

「表格描述定義」可以針對 JavaBean / Map 形式資料來源，以相同的 Table-Metadata 轉換為不同格式(PDF/Excel/CSV)輸出，方便變換報表格式時處理。

## 原始資料 (Data Model)

* 將被轉換為表格的原始資料，應為 Iterable<T> 類型，每一筆資料列的內容，存在一個類別為 T 的物件中。

## 表格描述定義(TableMetadata)

* 表格描述基本定義，是一對一設定直欄標頭與資料來源對應。如下例增加一個「年度」欄位，其資料值「new BeanProperty("text1")」表示由 java bean 的 "text1" 屬性取得內容。

```java
final TreeTableMetadata metadata = new TreeTableMetadata ();
metadata.append("年度", new BeanProperty("text1"));
```

* 底層升版到 JDK8 後，對 DocumentGenerator 介面新增 dataSource(...) 相關 default 函式。主要用於在不同的測試模式下，可改為輸出來源描述資訊。

```java
CellDataSource dataSource(String)       // 同 new BeanProperty(...)
CellDataSource[] dataSources(String...) // 

CellDataSource dataSource(BiFunction<CellDataSource, CellDataSource, ? extends CellDataSource>, String, String)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, CellDataSource)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, String)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, CellDataSource...)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, String...)
```

* 一些新增欄位定義的函式如下，後續章節會再逐一介紹。

```
// 基本新增用法
append(String title)
append(String title, CellDataSource source)
append(String title, float widthWeight)
append(String title, CellDataSource source, float widthWeight)

// 新增後，以 Consumer 操作所加入的欄位描述資訊
append(String title, Consumer<C>)
append(String title, CellDataSource source, float widthWeight, Consumer<C>)

// 以 ColumnDefine 介面新增欄位，通常用於使用 ENUM 定義固定欄位選項。
append(ColumnDefine)

// 加入無標題欄位
append(CellDataSource source, float widthWeight)
```

* 若不同資料列間，要利用某些欄位做群集設定，可使用 GroupingInfo createGroupingInfo(...) 及其相關類別處理。
* 另外，TableMetadata也可以附加 Band 資訊，以定義轉換表格時，所需顯示的額外區塊。

## 表格轉換器(TableTransfer)

有原始資料，有表格描述定義，就可以選用不同的表格轉換器轉換為表格輸出。

最基本的轉換器是以文件格式分類：PDFTableTransfer、ExcelTableTransfer、CSVTableTransfer，把表格內容轉為各自格式的最適化輸出。如 PDF 遇到寬表格可做分頁處理，CSV 可把巢狀表格扁平化處理。

但也可以為特殊的轉換需求，選用其它的表格轉換器，如：

* PDFDuplicatedTableTransfer 
  * 特殊資料清冊，不支援統計群集功能，將直列表格轉成多欄依序輸出。
  
* 客製化 TableTransfer 
  * 例如遇到群集資料時，要特別做換頁處理或其它格式輸出。











