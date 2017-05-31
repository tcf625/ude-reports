# 概述


「文件」類型，是對於頁面控制支援度最齊全的格式。



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




本章主要以

AbstractPDFGenerator