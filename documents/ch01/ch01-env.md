## 環境與設定

Ude-Report 基於 UDE 套件開發，一般運作於 Spring 環境之下，但需要準備的元件只有 PDFDocumentManager 及其設定檔。

``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
   <constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
   
```

若建構 PDFDocumentManager 時，未以參數傳入設定檔資源路徑，將使用隨同發布的預設 'classpath:itext-config-default.properties'。









