## 環境與設定

Ude-Report 基於 UDE 套件開發，一般運作於 Spring 環境之下，但現在需準備的元件只有 PDFDocumentManager 一個。

``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager" />   
```

預設會使用隨同發布的 'classpath:itext-config-default.properties' 做為設定檔，其完整內容於下節說明。


''' properties
watermarks.config.path=${resource.path}/reports/marks/
font.WindowsFont.MINGLIU.0 = ${global.resource.path}/fonts/mingliu.ttc
font.WindowsFont.MINGLIU.2 = ${global.resource.path}/fonts/mingliub.ttc
font.WindowsFont.KAI.0 = ${global.resource.path}/fonts/kaiu.ttf
font.CNS11643.SUNG.0=${global.resource.path}/fonts/TW-Sung-98_1.ttf
'''


``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
<constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
```








若建構 PDFDocumentManager 時，未以參數傳入設定檔資源路徑，將使用。

