# 環境與設定

Ude-Report 基於 UDE 套件開發，一般運作於 Spring 環境之下，但需要準備的元件只有 PDFDocumentManager 及其設定檔。下例中若未以 constructor-arg 傳入設定檔資源路徑，預設將使用隨同發布的 'classpath:itext-config-default.properties'。


``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
   <constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
   
```







