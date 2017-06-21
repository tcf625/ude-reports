### 欄位寬度定義

以


#### 實作說明

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

