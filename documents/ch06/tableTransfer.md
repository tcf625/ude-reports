## 其它表格轉換器



* PDFDuplicatedTableTransfer 
  * 特殊資料清冊，不支援統計群集功能，將直列表格轉成多欄依序輸出。
  

## 表格轉換基本流程(參考)


``` java 
// ! RESET
this.resetProcessInfo(values);
    // this.resetGrouping();
    // this.evalAllGrouping(values, UdeRuntimeUtils::doNothing);
    // this.resetAllColumnsValue();

this.processBand(BandType.TableHeader);

// ! TABLE
this.processTableStart();

// ! HEADER
this.transColumnHeaderPart();

// ! BODY
this.transBodyPart(values);
// 
//  TableHeaderRepeat 
//  TableFooterRepeat 
//  foreach dataRow{
//    this.processDataRow(this.bodyBlock, realData);
//  
// processPreGrouping(); 
// ! 根據現有資料與先前資料比對，決定統計層級及是否需輸出(於前方的案例)。
// this.drawDataRow(bodyBlock, realData); // ! 處理本列實際輸出。
// this.processPostGrouping();            // ! 處理統計輸出於資料後方的案例。
//  } 
//


if (this.dataIndex == 0) {
    this.processBand(BandType.NoData);
}

// ! END
this.processTableEnd();
this.processBand(BandType.TableFooter);

// ! CHARTS
final List<ChartBand> charts = this.tableMetadata.getCharts();
for (final ChartBand chartBand : charts) {
    this.processChartBand(chartBand);
}


} finally {     this.tableMetadata.reset(false);     }
```
