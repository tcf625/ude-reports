## 邊界控制

* 適用於 PDF / EXCEL

![UDE-PDF](/assets/ch01-layout-pdf.png)

再回顧一下基本概念中看到的「頁面版型結構」，使用 BaseLayoutInfo 可定義其中的定位資訊。




![](/assets/ch02/pages_margin-small.png)



## 換頁控制


頁面大小與邊界，在文件生成的過程中是可以改變的。當目前頁面已有內容輸出時，頁面變動會在下一頁才生效。但是邊界變動可能會導致部分頁首頁尾輸出不如預期，使用時請自行確認當下文件輸出狀態。換言之，建議把PageSize與LayoutInfo的異動緊接在newPage() 之後進行。

但一般不建議在文件中變動頁面大小，可能會困擾使用者列印輸出或做其它處理。