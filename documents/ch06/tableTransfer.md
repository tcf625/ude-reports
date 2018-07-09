## 其它表格轉換器



* PDFDuplicatedTableTransfer 
  * 特殊資料清冊，不支援統計群集功能，將直列表格轉成多欄依序輸出。
  

## 表格轉換基本流程(參考)

* 重設所有處理資訊。_this.resetProcessInfo(values);_
  *  群集計算資訊
  *  具狀態的 CellDataSource (如 counter)
* 繪製 BandType : TableHeader
* 初始表格物件   processTableStart();_
* 準備表頭(ColumnHeader)內容 
  * 含 BandType : TableHeaderRepeat / TableFooterRepeat 
* 輸出表身(ColumnBody)內容    transBodyPart(values);_
  * 針對每一筆資料，輸出資料列。
    * [PDF 格式] 一併處理換頁控制。
    * [含群集設定時] 於輸出前後，檢查是否輸處理群組列。
* 表格繪製結束 _this.processTableEnd();_
  * 輸出所有暫存資料(含計算TableFooter空間)。
  * 繪製 BandType : TableFooter
