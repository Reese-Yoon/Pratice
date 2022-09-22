## board-app에서 웹 애플리케이션 제작(Servlet 이용)

### 조회수 증가하기
 - MariaDBBoarDao 수정
    - findByNo 메서드에 쿼리문을 추가!
    ```java
    PreparedStatement pstmt2 = con.prepareStatement(
            "update app_board set vw_cnt = vw_cnt +1 where bno="+ no);

        ResultSet rs = pstmt.executeQuery();
        ResultSet rs2 = pstmt2.executeQuery()) {
    ```
    - findByAll 메서드에서 조회수 기준으로 내림차순 정렬!
    ``` java
    public List<Board> findAll() throws Exception {
      try (PreparedStatement pstmt = con.prepareStatement(
          "select bno,title,mno,cdt,vw_cnt from app_board order by vw_cnt desc");
          ResultSet rs = pstmt.executeQuery()) {

        ArrayList<Board> list = new ArrayList<>();

        while (rs.next()) {
          Board board = new Board();
          board.no = rs.getInt("bno");
          board.title = rs.getString("title");
          board.memberNo = rs.getInt("mno");
          board.createdDate = rs.getDate("cdt");
          board.viewCount = rs.getInt("vw_cnt");

          list.add(board);
        }

        return list;
      }

    ```

    <img src="src\main\java\com\bitcamp\board\1.png" width="700" height="370">
    <img src="src\main\java\com\bitcamp\board\2.png" width="700" height="370">
    <img src="src\main\java\com\bitcamp\board\3.png" width="700" height="370">



 ### 오류 처리
  - connected-refused : servers 폴더에 server.xml 설정파일 안에서 port번호를 확인하자!
  - could not launch in profiling mode because no profilers are configured. : eclipse-workspace 에서 C:\Users\master\eclipse-workspace\.metadata ~ tmp0\wtpwebapps폴더에 프로젝트파일을
  지우고 나서 publish를 다시하여 생성시켜줘야 한다!