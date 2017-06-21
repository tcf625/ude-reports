即一般最常見的報表型式，可有階層關係的標題列在最上方，但每列資料只佔一列。同一列之間的欄位可能有相依關係，如相加、相減。所有資料也可能依其它欄位做分組統計。如下表即是一個典型範例。


``` java
    @Test
    public void test_basicTable() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 20);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 20);
        metadata.append("值1", new BeanProperty("value1"), 20);
        metadata.append("值2", new BeanProperty("value2"), 20);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

```

