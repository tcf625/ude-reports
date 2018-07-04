# 開發環境設定(ECLIPSE)

[圖文版 GOOGLE DOC - 待編輯](https://docs.google.com/document/d/16MGbWAmmaZarmFtODbu5CbMC6YMVXGfVoRMYrLRDlwM/edit?usp=sharing)

## 下載路徑

- 上傳日期 (2017/01/17)
- http://192.168.10.8/sris/eclipse/eclipse-jee-neon-4-6-SRIS4.tar.gz

## plug-in 清單

- JBoss Tool
- Subclipse
- EclEmma Java Code Coverage 2.3.3.201602231923 com.mountainminds.eclemma.feature.feature.group Mountainminds GmbH & Co. KG
- Properties Editor 6.0.4 jp.gr.java\_conf.ussiy.app.propedit.feature.group
- Eclipse Checkstyle Plug-in 6.19.1.201607051943 net.sf.eclipsecs.feature.group http://eclipse-cs.sf.net/

# ECLIPSE 安裝流程

-   前置 : 已安裝 git 完成。
    -   安裝 git : https://git-scm.com/downloads
        -   **注意 CRLF 轉換設定，應取消自動轉換！**
    -   安裝 tortoisegit (GUI for windows) : https://tortoisegit.org/
-   前置 : 已安裝 JDK8 完成。
-   前置 : 下載 TOMCAT 8，並解開。

## 自 git 簽出專案 source code

- 建立專案主目錄，如 D:\\_SRIS3_3.0_GIT。
  - 到該目錄右鍵選 git bash here : 並輸入  
``` git
    # 只需第一次更新 SSL 設定，日後若要重下專案，直接下CLONE 指令即可。
    git config --global http.sslVerify false 
    git clone https://sris.pj/git/greenc.git
```  

## Eclipse 安裝

### 解開 Eclipse
  - 將 tar.gz 檔放至 eclipses 目錄，如 C:\\Java\\Eclipses\\
  - 使用 Git-Bash OR CYGWIN 至該目錄下，以 tar 解開壓縮檔。如
```
  tar -xf eclipse-jee-neon-4-6-SRIS4.tar.gz
```

### 建立 workspace
  - 進入 eclipse-jee-neon-4-6-SRIS4 目錄
  - 解開 \_workspace\_blank.tar.gz，並改名 \_workspace\_sris3
```
cd eclipse-jee-neon-4-6-SRIS4
tar -xf _workspace_blank.tar.gz
mv _workspace_blank _workspace_sris3
```

### 修改 eclipse.ini (更換 Jvm 路徑)
  - notepad eclipse.ini  
  - (或) vi eclipse.ini 
  - 找到以下 -vm 或新增 -vm 指到本機 JDK8 
```
-vm
C:/Java/jdk1.8.0_92/bin/javaw.exe 
```

### 建立開啟 eclipse WINDOWS 捷徑
  -  在 WINDOWS 的捷徑內容，「捷徑」-&gt;「目標」加上 -data {workspace\_path} ，如

```
C:\Java\Eclipses\eclipse-jee-neon-4-6-SRIS4\eclipse.exe 
    -data C:\Java\Eclipses\eclipse-jee-neon-4-6-SRIS4\_workspace_sris3
```


## 首次啟動 Ecplipse

## 修改、確認個人 workspace 設定
 -   點選捷徑開啟 eclipse ，使用 \_workspace\_sris3 為 workspace。
   -   修改 eclipse 中的 JDK 路徑
   -   修改 eclipse 中的 TOMCAT 路徑
   -   修改 eclipse中，DEFAULT 使用的 JRE 參數 -DBASEDIR=${sris4-dev-config-path} 為 LOCAL 下載 CONFIG 的路徑。如：

```
  -DBASEDIR=D:\01_RIS_SRIS3_3.0_GIT\greenc\CONFIG 

```

##  import 現有 maven 專案

  * **Import... -> Existing Maven Projects**