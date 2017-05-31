# 概述


「文件」類型，是對於頁面控制支援度最齊全的格式。在 DocumentGenerator 介面中，與頁面控制相關的項目有以下幾項
。

``` java
default public PrintPageSize getPrintPageSize() {
    return PrintPageSize.DEFAULT;
}
default BaseLayoutInfo prepareLayoutInfo() {
    return null;
}
default List<DocumentGenerator> getSections() {
    return Arrays.asList(this);
};
```

## PrintPageSize 頁面大小



本章主要以

AbstractPDFGenerator