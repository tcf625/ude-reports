## 浮水印

* **適用於 PDF**

浮水印與騎縫章是相似的概念，都是輸出在文件底部的圖片或文字，差別在於騎縫章會把一張圖片切為兩半，分別輸出在不同頁面的首尾。  
兩者的定義方式都是產出對應的MarkInfo，在產生PDFDocument時傳入List&lt;? extends MarkInfo&gt; markInfos。  
或在 PDFGenerator 實作 prepareMarkInfos\(\)

套件內建的浮水印、騎縫章都統一使用PropertiesMarkInfo進行定義，依不同形式的浮水印、騎縫章，各自有對應的MarkmakerProperties 實作子類別。

### 文字浮水印

文字型態浮水印資訊的實作類別是 TextWatermarkProperties，預設建構子為輸出文字，預設字型使用PDFDocument的定義，所以通常必要再設定它的FontType及FontSize。  
不特別指定角度的話，預設依內頁範圍的左下-右上對角輸出。

### 線段浮水印

以繪圖模式輸出指定線段樣式

![](/assets/ch02/watermark_lines_sample.png)

### 圖片浮水印

圖片浮水印資訊的實作類別是ImageWatermarkProperties，預設建構子為filePath。若圖檔資源隨同JAR檔一並發布，則可用classpath:${resource-path}格式指定。

