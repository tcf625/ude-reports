pages\_margin.png



![](/assets/ch02/pages_margin-small.png)

``` java
@Test
public void test_Margin() {
    super.createPDF(pdfDocument -> {
        // ! 定義頁面大小.
        pdfDocument.setPageSize(PageSize.A4.rotate());
        // ! 定義四周邊界大小.
        final float marginLeft = LengthUnit.CM.trans(2.54f);  // 以 公分為單位
        final float marginRight = 36;                         // 以 pixel 為單位
        final float marginTop = LengthUnit.MM.trans(12.7f);   // 以 公厘為單位
        final float marginBottom = LengthUnit.INCH.trans(1);  // 以英吋為單位
        final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);

        // ! 定義上下頁首尾間距.
        layoutInfo.setHeaderExtra(36);
        layoutInfo.setFooterExtra(72);

        // ! 設定版面資訊
        pdfDocument.setLayoutInfo(layoutInfo);
    });
}
```


