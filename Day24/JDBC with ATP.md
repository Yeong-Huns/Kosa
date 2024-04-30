
---
 > JDBC with ATP
--- 

# Java Connectivity with Autonomous Database (ATP or ADW) using 19c and 18.3 JDBC

* To get the JDBC drivers from Oracle Technical Resources, follow these steps.

* [Oracle_Docs](https://docs.oracle.com/en/cloud/paas/autonomous-database/atpdg/java-application.html#GUID-6B2610C1-120C-46F0-876C-E7D871CEC267)
* [Oracle_Database](https://www.oracle.com/database/technologies/appdev/jdbc-ucp-183-downloads.html)

-> ojdbc8-full.tar.gz 파일 내려받아 압축 해제

* *ATP 설정을 모두 마친 상태일 것!

* *ATP Wallet 파일 압축 풀기

* 프로젝트 생성

* *프로젝트에 lib 폴더 생성

* 압축 해제해 둔 ojdbc8-full 폴더의 모든 jar 파일 lib 폴더에 붙여넣기

* 프로젝트의 Java Build Path 설정에서 lib 폴더의 모든 jar 파일을 Classpath에 추가

* `oracle.properties` 파일 설정

```properties
driver=oracle.jdbc.driver.OracleDriver
url=jdbc:oracle:thin:@edudb_high?TNS_ADMIN=C:\dev\wallet\Wallet_edudb
user=user01
password=kosa2024oraclE
```

> 📌 `DataBase Connection`
> - DBConnection.java 파일 생성
> - JdbcTestSelect.java 파일 생성 및 실행
> - SEVERE: Attempt To Configure ONS In FanManager Failed   
 
https://support.oracle.com/knowledge/Oracle%20Cloud/2616175_1.html


---
> oracle driver thin 방식 연결 정리
---

  SEVERE: Attempt To Configure ONS In FanManager Failed

* [StackOverFlow](https://stackoverflow.com/questions/58873384/spring-boot-app-error-log-says-attempt-to-configure-ons-in-fanmanager-failed-wit)

>   위 사이트를 참고하여 classpath 설정에서 simplefan.jar와 ons.jar 파일을 제거하여 
  연동하니 thin 방식으로도 문제 없이 빠르게 연동되는 것을 확인하였습니다.



