
# 表格轉換架構

「表格描述定義」可以針對 JavaBean / Map 形式資料來源，以相同的 Table-Metadata 轉換為不同格式(PDF/Excel/CSV)輸出，方便變換報表格式時處理。

## 原始資料 (Data Model)

將被轉換為表格的原始資料，應為 Iterable<T> 類型，每一筆資料列的內容，存在一個類別為 T 的物件中。


本章介紹製表所用的基礎API，理論上所有的表格皆可以用基礎繪製，但是可使用「表格描述定義」時，還是建議盡量使用，以簡化程式開發。

基礎API 適用的應用情境包含格式複雜的申請書、跨頁的大型表格、格式簡單的名條、頁首頁尾特殊排版。


## 表格描述定義(TableMetadata)


表格描述基本定義，是1-1對應設定直欄標頭與資料來源。如下例為 metadata 增加一個「年度」欄位，其資料值「new BeanProperty("text1")」表示由 java bean 的 "text1" 屬性取得內容。

```java
final TreeTableMetadata metadata = new TreeTableMetadata ();
metadata.append("年度", new BeanProperty("text1"));
```

底層升版到 JDK8 後，新增 DocumentGenerator 介面，並定義 dataSource(...) 相關函式。
主要在不同的測試模式下，可改為輸出來源描述資訊。

```java
CellDataSource dataSource(String)       // 同 new BeanProperty(...)
CellDataSource[] dataSources(String...) // 

CellDataSource dataSource(BiFunction<CellDataSource, CellDataSource, ? extends CellDataSource>, String, String)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, CellDataSource)
CellDataSource dataSource(Function<CellDataSource, ? extends CellDataSource>, String)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, CellDataSource...)
CellDataSource dataSource(Function<CellDataSource[], ? extends CellDataSource>, String...)

```


利用本套件輸出此類表格的設定方式，第一步是先建立 xxxTableMetadata，再插入個別欄位定義\(含標頭、資料來源及格式\)、群組定義。最後再以 PDFTableTransfer、ExcelTableTransfer 等表格轉換器，撘配原始資料List《JavaBean/Map》轉換為表格輸出。



## 表格轉換器(TableTransfer)



# 表格描述基本準則



UDE-Report 的表格描述基本準則，是1-1對應設定直欄標頭與資料來源。如下面建立 TableMetadata 的範例，為 metadata 增加一個「年度」欄位，其資料值「new BeanProperty("text1")」表示由 java bean 的 "text1" 屬性取得內容。



一些新增欄位定義的函式如下，後續章節會再逐一介紹。


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

