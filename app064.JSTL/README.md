# app064. JSTL 사용법
064. JSP에서 자바 코드를 제거하기: JSTL 사용
---------------------------------------
## 작업 내용
* 1단계 
  - JSTL 라이브러리를 프로젝트에 포함한다.
  - search.maven.org 에서 "JSTL"로 검색한다.
  - 빌드 스크립트 파일(build.gradle)에 라이브러리 등록한다.
  - gradle eclipse 실행한다.
  - eclipseIDE에서 프로젝트를 갱신한다.
  
* 2단계 
  - JSP 페이지에 JSTL 태그를 사용한다.
  - JSP 페이지에 태그 라이브러리를 선언한다.
  - 필요한 JSTL 태그를 사용한다.
  
  
--------------------------------------------
## 개념정리
### JSTL 사용법
: Java Server Pages Standard Tag Library의 약자로 JSP에서 사용하는 태그 라이브러리를 공통으로 사용하기 위해 정해진 표준
 - core : 프로그램 개발 시 사용되는 기본적인 기능들이 포함.
 - formatting : 날짜, 시간에 관한 형식을 처리하는 기능
 - sql : 데이터베이스 작업에 관한 기능들을 수행하는 태그
 - xml : xml을 지원하는 기능의 태그들
 - functions :  여러 가지 함수 기능을 제공
 
각 태그들을 사용하기 위해 taglib 지시자에 지정하는 prefix와 uril속성값은 다음과 같다
 - core : <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 - Formatting : <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 - SQL : <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
 - XML : <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
- Functions : <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

설치)  tomcat.apache.org사이트에서 내려받은 jstl파일을 웹 애플리케이션에서 사용하려면 /WEB-INF/lib 폴더에 있어야 합니다. 
/WEB-INF/lib 폴더에 위차한 Jar파일들은 WAS가 자동으로 인식하게 됩니다.

### core

### formatting

### SQL

### XML

### functions