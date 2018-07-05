### 巢狀表格

``` java
final NestTableMetadata metadata = new NestTableMetadata();
metadata.append("年度", new BeanProperty("text1"));
metadata.append("地區", new BeanProperty("text2"));
metadata.append("項目", new BeanProperty("text3"));
metadata.subTable(subTable -> {
    subTable.append("值1");
    subTable.append("值2");
    subTable.nextRow();
    subTable.append("值3");
    subTable.append("值4");
    subTable.append("值5");
    subTable.nextRow();
    subTable.append("值A");
    subTable.append("值B");
    subTable.append("值C");
    subTable.append("值D");
});
```

UDE-Report 所定義的巢狀表格，其巢狀新增方式是一塊塊插入子表格的做法。
與 TreeTableMetadata 相較，產出 PDF 的欄位 LAYOUT 會比較有彈性。如此例中，值1~5/A~C 欄位所佔寬度不同且未對齊；但生成EXCEL時，因試算表的天生限制，加上輸出原則為盡量不合併EXCEL欄位，所以生成結果會與 PDF 文件有些許不同。

* (見下圖第二列)

![](/assets/ch06/nestTable-basic.png)


### 資料框線

為使人閱讀清冊報表時，可以較容易區別單筆資料，
NestTableMetadata 預設把每一組資料所佔的列，用粗線外框標示。

可使用 nestMetadata.setBorderWidth(0F); 取消此行為。

### 無框線表格

產出無框線表格時，一般也會同時把資料框線設為不顯示。


``` java
    final NestTableMetadata metadata = new NestTableMetadata();
    metadata.getDefaultFormat().setBorder(Border.N);
    metadata.setBorderWidth(0F);
```

#### output


### 無框線表格 加 底線分割


``` java
    final NestTableMetadata metadata = new NestTableMetadata();
    metadata.getDefaultFormat().setBorder(Border.N);
    metadata.getDefaultHeaderFormat().setBorder(Border.B);
    metadata.setBorder(Border.B);
    metadata.setBorderWidth(0.25f);
```


#### output


## TODO : 待增加更多特殊使用案例說明。









