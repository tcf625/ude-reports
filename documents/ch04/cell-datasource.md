
Temp
======

2.0.2 移除註記


* ColValue extends HorizontalExpression<Object> 
  * 對應到其它 column-metadata / 已有替代類別
  

*  DateAddition extends HorizontalExpression<Object> {  
  * 日期計算，未使用。
  
* //public class ExcelFunction<R> extends HorizontalExpression<R> {   
  * 待重新設計
``` java 
//    //        NumberOP a = new ColNumber(col_1)//
//    //                .add(col_2)//
//    //                .add(col_3)//
//    //                .subtract(col_4);
//    //        ;
//    //        ColValue v4 = new ColValue(col_4);
//    //
//    //        ExcelFunction<Object> excelFunction = new ExcelFunction<Object>("DIFF", a, v4);
```