## 環境與設定

Ude-Report 基於 UDE 套件開發，一般運作於 Spring 環境之下，但需準備的元件只有 PDFDocumentManager 一個。

``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager" />   
```

預設使用隨同發布的 'classpath:itext-config-default.properties' 做為設定檔，其完整內容於下節說明。但其中有幾項定義需用到宣告 UdeBasicConfiguration 後建立的路徑環境變數 ${resource.path} 及 ${global.resource.path}。若希望脫離 UDE 甚或 Spring 環境執行，就必須自行修改設定檔，並用以建立 PDFDocumentManager 元件。

``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
<constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
```