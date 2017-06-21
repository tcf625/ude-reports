# 表格描述定義

利用本套件輸出此類表格的設定方式，第一步是先建立 xxxTableMetadata，其後再插入個別欄位定義\(含資料來源及格式\)、群組定義。

最後再使用PDFTableTransfer、ExcelTableTransfer等表格轉換器，撘配原始資料\(List&lt;JavaBean/Map&gt;\)轉換為表格輸出。

```
// 新增後，以 Consumer 操作所加入的欄位描述資訊
append(String, Consumer<C>)
append(String, CellDataSource, float, Consumer<C>)

// 以 ColumnDefine 介面新增欄位，通常用於使用 ENUM 定義固定欄位選項。
append(ColumnDefine)

// 加入無標題欄位
append(CellDataSource, float)
//
append(String)
append(String, CellDataSource)
append(String, CellDataSource, float)
append(String, float)
```

TableMetadata 的建立方式如下，後續章節會逐一介紹 TableMetadata 的各種特性。
在下例中的 new BeanProperty("text1") 敘述表示此欄位資料值將由 JavaBean 的 text1 屬性取得。

```java
final TreeTableMetadata metadata = new TreeTableMetadata ();
metadata.append("年度", new BeanProperty("text1"));
// ...
```



