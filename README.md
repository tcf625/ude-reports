# UDE - Reports 說明文件與範例

所有相關範例，目前需於公司內網執行，以取得相關 libraries

Maven Repository Setting :

```xml
<repositories>
    <repository>
        <id>IISI_UDE</id>
        <url>http://192.168.57.21/nexus/content/groups/public</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>interval:5</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```

## 概述

UDE-Reports 是基於 iText 及 Apache POI 開發的報表產製輔助套件。

主要目的為簡化 iText 及 Apache POI 產製PDF及Excel文件的程式語法。

針對通用性的版面Layout及表格，可使用同一組描述資料與資料集，產出不同格式的輸出結果，以減少重複程式開發。

