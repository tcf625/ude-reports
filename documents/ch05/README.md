# 繪圖、定位輸出


PaintTool 用於精準輸出圖形、文字於定位的工具類別。需配合 Coordinate 建立 PaintTool 物件，預設有CM_BL、CM_TL、Default三個共用實例，其單位分別為公分、公分、pixel，座標軸原點分別在左下、左上、左下。

若需設定PdfContentByte樣式，請使用iText原生API。




## 線段輸出

* drawLines(PdfContentByte, Float, Float...)
* drawBlock(PdfContentByte, PointF, PointF)
* drawBlock(PdfContentByte, PointF, PointF, float offset)
* drawBlock(PdfContentByte, SimpleRectangle, float offset)

## 文字輸出

* drawText(PdfContentByte, String, float, float)
* drawText(PdfContentByte, String, float, float, float)
* drawTextAlignLeft(PdfContentByte, String, float, float)
* drawTextAlignLeft(PdfContentByte, String, float, float, float)
* drawTextBlock(PdfContentByte, Font, String, float, float, float, float)
* drawTextBlock(PdfContentByte, Phrase, float, float, float, float)

## 圖片輸出

* drawImageAlignLeft(PdfContentByte, File, float, float)
* drawImageAlignLeft(PdfContentByte, File, float, float, ScaleStrategy)

## 表格輸出

* drawTable(PdfContentByte, TableiText, float, float)

