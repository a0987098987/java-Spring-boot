<!-- <%@ page language="java" contentType="text/html;charset=UTF-8" %> -->
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<pre>
  利用 maven 建置一個 Spring-boot 專案,實作以下內容。
  Java:8
  資料庫:<a href="http://localhost:8080/h2-console/">H2</a>(OpenJPA / Spring Data JPA)
    
  功能簡述:
  1. 呼叫 coindesk API,解析其下行內容與資料轉換,並實作新的 API。
  2. 建立一張幣別與其對應中文名稱的資料表(需附建立 SQL 語法),並提
  供 查詢 / 新增 / 修改 / 刪除 功能 API。
  實作內容:
  1. 幣別 DB 維護功能。
  2. 呼叫 coindesk 的 API。
  3. 呼叫 coindesk 的 API,並進行資料轉換,組成新 API。<a href="http://localhost:8080/api/download">Download</a>
  此新 API 提供:
  A. 更新時間(時間格式範例:1990/01/01 00:00:00)。
  B. 幣別相關資訊(幣別,幣別中文名稱,以及匯率)。
  
  請撰寫以下各項功能之單元測試:
  1. 測試呼叫查詢幣別對應表資料 API,並顯示其內容。<a href="http://localhost:8080/api/get">All</a>
  <a href="http://localhost:8080/api/get/USD">查詢USD</a>
  <a href="http://localhost:8080/api/get/GBP">查詢GBP</a>
  <a href="http://localhost:8080/api/get/EUR">查詢EUR</a>

  2. 測試呼叫新增幣別對應表資料 API。
  http://localhost:8080/api/post
  {"code":"TWD", "chinese":"台幣", "rate_float":"1", "updated":"1990/01/01 00:00:00"}

  3. 測試呼叫更新幣別對應表資料 API,並顯示其內容。
  http://localhost:8080/api/put/USD
  {"code":"USD", "chinese":"美金", "rate_float":"31.85", "updated":"1990/01/01 00:00:00"}
  {"code":"GBP", "chinese":"英鎊", "rate_float":"40.3" , "updated":"1990/01/02 00:00:00"}
  {"code":"EUR", "chinese":"歐元", "rate_float":"33.54", "updated":"1990/01/03 00:00:00"}

  4. 測試呼叫刪除幣別對應表資料 API。
  http://localhost:8080/api/del/USD
  http://localhost:8080/api/del/GBP
  http://localhost:8080/api/del/EUR

  5. 測試呼叫 coindesk API,並顯示其內容。<a href="http://localhost:8080/api/demo5">Demo</a>
  6. 測試呼叫資料轉換的 API,並顯示其內容。<a href="http://localhost:8080/api/demo6">Demo</a>
  
	</pre>
</body>
</html>