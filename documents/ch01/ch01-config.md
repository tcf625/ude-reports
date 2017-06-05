### 設定檔

設定檔使用 UDE-Extended properties 格式撰寫，預設的 itext-config-default.properties 內容如下：


``` properties

# 預設字型
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

watermarks.config.path=${resource.path}/reports/marks/

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
# 依 http://www.cns11643.gov.tw/AIDB/cns_authorization_apply.do
# 全字庫字型逕行至「政府資料開放平臺」http://data.gov.tw/node/5961下載
#
font.CNS11643.SUNG.0=${global.resource.path}/fonts/TW-Sung-98_1.ttf
font.CNS11643.SUNG.2=${global.resource.path}/fonts/TW-Sung-Ext-B-98_1.ttf
font.CNS11643.SUNG.F=${global.resource.path}/fonts/TW-Sung-Plus-98_1.ttf

font.CNS11643.KAI.0=${global.resource.path}/fonts/TW-Kai-98_1.ttf
font.CNS11643.KAI.2=${global.resource.path}/fonts/TW-Kai-Ext-B-98_1.ttf
font.CNS11643.KAI.F=${global.resource.path}/fonts/TW-Kai-Plus-98_1.ttf


```

#### 字型設定

主要的設定值有兩項：'default.font' 與 'default.font.size' ，分別定義文件產出時預設使用的字型代碼與大小。
預設範本使用「WINDOWS-細明體」輸出文字。

``` properties
default.font=WindowsFont.MINGLIU
default.font.size=12
```

##### UNICODE 多字面

實際使用的字型檔案則以 font.{字型代碼}.{UNICODE字面} 進行定義。如下例中，font.CNS11643.SUNG.0 為全字庫宋體字第0字面，font.CNS11643.SUNG.F為全字庫宋體字第15字面。

若使用完整UDE套件及相關設定方式，設定路徑可使用${global.resource.path}、${resource.path}等環境變數，讀入設定檔時會自動代換。

``` properties
font.CNS11643.SUNG.0=${global.resource.path}/fonts/TW-Sung-98_1.ttf
font.CNS11643.SUNG.2=${global.resource.path}/fonts/TW-Sung-Ext-B-98_1.ttf
font.CNS11643.SUNG.F=${global.resource.path}/fonts/TW-Sung-Plus-98_1.ttf
```

##### 多個字型檔案

如相同的字面設定有多組，則套件啟動時，會逐一測試各檔案路徑是否存在可用字型檔。像下例中把常用的 WINDOWS 標楷體字型所在目錄都做了設定，但只有實際存在的檔案會被引入，其它項目則被忽略。

``` properties
font.WindowsFont.KAI.0 = C:/windows/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = D:/windows/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = C:/winnt/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = D:/winnt/fonts/kaiu.ttf
font.WindowsFont.KAI.0 = ${global.resource.path}/fonts/kaiu.ttf
```

專案有使用自造字者，也可以使用多字型檔案設定方式，把自造字檔案加到第二組。


``` properties
font.NPP_FONT.MING.0=${global.resource.path}/font/TW-Sung-98_1.ttf
font.NPP_FONT.MING.0=${global.resource.path}/font/FNPMing.ttf
``` 

##### TTC 字型檔

TrueType Collections (TTC)允許將多個字型合併為一個檔案，為選擇的字型僅使用一些字型的不同字形建立替換間距儲存。若使用第二組以後的定義，如「新細明體」，可使用下列方式設定。

```
font.WindowsFont.NEW-MINGLIU.0 = C:/windows/fonts/mingliu.ttc,1
font.WindowsFont.NEW-MINGLIU.2 = C:/windows/fonts/mingliub.ttc,1
```


