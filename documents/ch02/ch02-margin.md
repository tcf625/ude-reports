## 邊界控制

* **適用於 PDF / EXCEL**

![UDE-PDF](/assets/ch01-layout-pdf.png)

再回顧一下基本概念中看到的「頁面版型結構」，使用 BaseLayoutInfo 可定義其中的定位資訊。

單位為 pixel，可以使用 LengthUnit.XXX.trans() 進行換算，如下例為公分、公厘、英吋不同單位設定結果。
_(邊界虛線以測試套件的 showMarginBorder=TRUE 輸出。)_



![](/assets/ch02/pages_margin-small.png)


## 換頁控制

* **適用於 PDF**

要手動對PDF進行換頁，請呼叫 PDFDocument.newPage()，若當前頁面還沒有實際內容輸出時，並不會真的換頁。所以如果要輸出空白頁面，請在兩次換頁間插入一次 writeText(“”)。

輸出內容後，如果要確認是否造成換頁，可以用pdfDocument.isPageChanged()、pdfDocument.isNewPageBegin() 兩個Method 進行判斷。當PageChanged為真，則換頁事件有被觸發。當NewPageBegin為真，表示還沒有任何內容輸出到新的頁面。



頁面大小與邊界，在文件生成的過程中是可以改變的。當目前頁面已有內容輸出時，頁面大小變動會在下一頁才生效。
但邊界變動可能會導致部分頁首頁尾輸出與預期不符，使用時請自行確認當下文件輸出狀態。換言之，建議把PageSize與LayoutInfo的異動緊接在newPage() 之後進行。

但一般不建議在文件中變動頁面大小，PAGESIZE不統一的文件可能會困擾使用者輸出列印或做其它處理。