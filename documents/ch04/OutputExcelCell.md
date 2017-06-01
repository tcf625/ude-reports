### Excel Sample

``` java
    @Test
    public void test_basic() {
        super.createExcel(document -> {
            final ExcelSheet<?> sheet = document.createSheet("sheet1");
            sheet.appendCell(new ExcelPoint(0, 0), "A1");
        });
    }
```



