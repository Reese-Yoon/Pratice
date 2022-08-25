// 1) 스레드 사용 전
// 2) 스레드 사용 후 => 패키지 멤버 클래스로 스레드 구현하기
//    Thread를 생성하기 위해 따로 패키지 멤버 클래스로 만듬
// 3) 인스턴스 생성 후 즉시 메서드 호출하기
//   코드 더 짧게 구현하기 위해
package com.study.Thread;

public class Test03 {
  public static void main(String[] args) {

    int count = 100;

    //    MyThread t1 = new MyThread(count);
    //    t1.start();
    new MyThread(count).start();

    for(int i=0;i<count;i++) {
      System.out.println(">>>" + i);
    }


  }

}
