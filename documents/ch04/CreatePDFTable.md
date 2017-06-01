


```
        super.createPDF(pdfDocument -> {
            final TableiText table1 = pdfDocument.createTable(80, 1);
            table1.addCell("A : 80%");
            table1.addCell("B");
            table1.appendMe();
            final TableiText table2 = pdfDocument.createTable(100, 1);
            table2.addCell("A : 100%");
            table2.addCell("B");
            table2.appendMe();
        });
```


        
        
        

```
@Test
public void test_basicWidths() {
        super.createPDF(pdfDocument -> {
            final TableiText table1 = pdfDocument.createTable(100, 5);
            addWidthInfoCells(table1);
            table1.appendMe();
            final float[] widths = { 1, 2, 3, 4, 5 };
            final TableiText table2 = pdfDocument.createTable(100, widths);
            addWidthInfoCells(table2);
            table2.setSpacingBeforeInCM(0.5F);
            table2.appendMe();
        });
    }

    private static void addWidthInfoCells(final TableiText table) {
        final float[] widths = table.getWidths();
        final float totalWidth = UdeArrayUtils.sum(widths);
        for (int j = 0; j < widths.length; j++) {
            table.addCell("" + j);
        }
        for (final float width : widths) {
            table.addCell("" + width + "/" + totalWidth);
        }
    }
```

