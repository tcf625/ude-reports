### 底色

欄位底色可直接使用 java.awt.Color 設定 backgroundColor

```java
final TableiText table = pdfDocument.createTable(98, 16);
table.getDefaultFormat().setFontSize(8);
for (int r = 0; r < 255; r += 32) {
    for (int g = 0; g < 255; g += 32) {
        for (int b = 0; b < 255; b += 32) {
            final CellFormat cf = new CellFormat().setBackgroundColor(new Color(r, g, b));
            table.addCell(String.format("%X%X%X", r, g, b), cf);
        }
    }
}
table.appendMe();
```

![](/assets/ch04/cellformat-bgcolor-2.png)

