### 列高設定

``` java 
pdfDocument.setFontSize(12); // set Default Size
pdfDocument.writeText("列高範例：");
// !
pdfDocument.writeText("default size (12)");
pdfDocument.writeText(toBlockText(7, 5));
// !
pdfDocument.writeText("size (10)");
pdfDocument.writeText(toBlockText(7, 4), 10);
// !
pdfDocument.writeText("設列高 = X 1.0");
final FontInfo font10 = pdfDocument.getFontInfo().asSize(10);
pdfDocument.paragraphBuilder() //
    .addText(toBlockText(7, 4), font10) // 字型 10
    .setMultipliedLeading(1F) // 設定列高 = X 1.0
    .appendMe();

```

![](/assets/ch03/sample-line-height.png)

### 混合字型輸出


``` java 
final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
final FontInfo redFont = pdfDocument.getFontFactory().createFontInfo(10, FontStyle.STRIKETHRU, Color.RED);
final FontInfo kaiFont = kaiFactory.createFontInfo(10, FontStyle.UNDERLINE, Color.BLUE, Color.LIGHT_GRAY);
final ParagraphBuilder builder = pdfDocument.paragraphBuilder();
builder.addText("中文，第二字面：")      //
        .addText("𠀝", redFont)     //
        .addText("(下/上)", kaiFont) //
        .appendMe();
```
                    

![](/assets/ch03/sample-mixFont.png)

