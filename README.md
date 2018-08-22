# UDE - Reports 說明文件與範例

目前相關 libraries 僅發佈於本公司內網，故所有相關範例應於內網環境執行。

* **範例程式 @ GitHub：** https://github.com/tcf625/ude-reports/
* ** Maven Repository Setting :**

``` xml
<repository>
<id>IISIUDE-PUBLIC</id>
<name>IISIUDE-PULBIC</name>
<url>http://192.168.57.11:8080/ude_maven</url>
<releases><enabled>true</enabled></releases>
<snapshots><enabled>true</enabled><updatePolicy>interval:5</updatePolicy></snapshots>
</repository>
```


## 概述

UDE-Reports 報表產製輔助套件基於 iText 及 Apache POI 開發，主要目的為簡化使用原生 API 產製 PDF 及 Excel 文件的程式語法。另外，針對基本的版面 Layout 及 表格，可使用同一組描述資料與資料集，以產出不同格式的輸出結果，減少重複程式開發。

## 特性

* PDF 文件產製、條碼、浮水印及檔案上鎖等功能。 
* 通用性表格定義描述 \(轉 PDF/EXCEL/CSV 表格\)。 
* 通用表格裝飾元件。
* 多樣化 PDF 頁首頁尾支援。 
* 可精準控制 PDF 圖形、文字輸出。 
* 支援 unicode 輸出非0字面中文 \(2字面、15字面\)。 
* 保留使用原生 API 彈性。
* 提供國內專案常見表樣的支援性。
  現成的報表軟體，也許只要用到一小部分功能，便能達成一般專案報表的多數需求；
  但本元件是處理其它較複雜需求所累積的經驗成果。

## 系統需求

* JDK 8
* iText 2.1.7
* POI
* 全字庫中文字型檔(選配)


