# 表格描述定義


利用本套件輸出此類表格的設定方式，第一步是先建立 xxxTableMetadata，其後再插入個別欄位定義(含資料來源及格式)、群組定義。

最後再使用PDFTableTransfer、ExcelTableTransfer等表格轉換器，撘配原始資料(List<JavaBean/Map>)轉換為表格輸出。

以下逐一介紹 TableMetadata 的可設定特性

