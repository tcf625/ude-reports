# 表格描述定義

利用本套件輸出此類表格的設定方式，第一步是先建立 xxxTableMetadata，再插入個別欄位定義\(含標頭、資料來源及格式\)、群組定義。最後再以 PDFTableTransfer、ExcelTableTransfer 等表格轉換器，撘配原始資料\(List&lt;JavaBean/Map&gt;\)轉換為表格輸出。


UDE-Report 的表格描述基本準則，是將直欄標頭與資料來源做1-1對應設定。
下面建立 TableMetadata 的範例中，為此 metadata 增加了一個「年度」欄位，其資料值「new BeanProperty("text1")」表示由 java bean 的 "text1" 屬性取得內容。

```java
final TreeTableMetadata metadata = new TreeTableMetadata ();
metadata.append("年度", new BeanProperty("text1"));
// ...
```

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

