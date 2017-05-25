# 環境與設定

Ude-Report 基於 UDE-Frameword 開發，一般運作於 Spring 環境之下，但需要準備的元件只有 PDFDocumentManager 及其設定檔。下例中若未以 constructor-arg 傳入設定檔資源路徑，預設將使用隨同發布的 'classpath:itext-config-default.properties'。


``` xml
<bean class="com.iisigroup.ude.report.itext2.PDFDocumentManager">
   <constructor-arg index="0" value="classpath:itext-config-sris3.properties" />
</bean>
   
```

## 設定檔

設定檔使用
預設的 itext-config-default.properties 內容如下：



``` properties

預設字型
default.font=WindowsFont.MINGLIU
default.font.size=12

# 預設邊界
default.document.marginLeft=30
default.document.marginRight=30
default.document.marginBottom=30
default.document.marginTop=30


default.encryption.enable=false
default.encryption.owner.password=
default.encryption.user.password=

#default.table.integer.format=#,##0
#default.table.decimal.format=#,##0.00

watermarks.config.path=${share.path}/reports/marks/

#字型定義
font.WindowsFont.MINGLIU.0 = C:/windows/fonts/mingliu.ttc
font.WindowsFont.MINGLIU.0 = D:/windows/fonts/mingliu.ttc
font.WindowsFont.MINGLIU.0 = C:/winnt/fonts/mingliu.ttc
font.WindowsFont.MINGLIU.0 = D:/winnt/fonts/mingliu.ttc
font.WindowsFont.MINGLIU.0 = ${global.share.path}/fonts/mingliu.ttc

font.WindowsFont.MINGLIU.2 = C:/windows/fonts/mingliub.ttc
font.WindowsFont.MINGLIU.2 = D:/windows/fonts/mingliub.ttc
font.WindowsFont.MINGLIU.2 = C:/winnt/fonts/mingliub.ttc
font.WindowsFont.MINGLIU.2 = D:/winnt/fonts/mingliub.ttc
font.WindowsFont.MINGLIU.2 = ${global.share.path}/fonts/mingliub.ttc

font.WindowsFont.KAI.0 = C:/windows/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = D:/windows/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = C:/winnt/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = D:/winnt/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = ${global.share.path}/fonts/kaiu.ttf
#
# http://www.cns11643.gov.tw/AIDB/cns_authorization_apply.do
# 逕行至「政府資料開放平臺」http://data.gov.tw/node/5961下載
#
font.CNS11643.SUNG.0=${global.resource.path}/fonts/TW-Sung-98_1.ttf
font.CNS11643.SUNG.2=${global.resource.path}/fonts/TW-Sung-Ext-B-98_1.ttf
font.CNS11643.SUNG.F=${global.resource.path}/fonts/TW-Sung-Plus-98_1.ttf

font.CNS11643.KAI.0=${global.resource.path}/fonts/TW-Kai-98_1.ttf
font.CNS11643.KAI.2=${global.resource.path}/fonts/TW-Kai-Ext-B-98_1.ttf
font.CNS11643.KAI.F=${global.resource.path}/fonts/TW-Kai-Plus-98_1.ttf




```

