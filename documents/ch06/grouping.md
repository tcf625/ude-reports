## 資料群集 & 統計

* 原則上建議用在 TreeTableMetadata


* TableTransfer 的基本轉換流程(參考)

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
