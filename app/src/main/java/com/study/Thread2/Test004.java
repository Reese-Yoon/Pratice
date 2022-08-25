// 1) 스레드 사용 전
// 2) 스레드 사용 후 : Runnable 구현체를 패키지 멤버로 만들어 Thread 실행한다.
//    Runnable(인터페이스) 사용
// 3) 패키지 멤버 변수 - > 스태틱 중첩 클래스로 만든다.
// 4) 스태틱 중첩 클래스 -> 로컬 클래스
// 5) 로컬 클래스의 특징을 활용하여 바깥 변수의 값을 받는 코드를 제거한다.
//    왜? 컴파일러가 자동으로 그런 일을 할 코드를 생성해주기 때문이다.
package com.study.Thread2;

public class Test004 {
  public static void main(String[] args) {

    int count = 10;

    class MyRunnable implements Runnable{
      @Override
      public void run() {
        for(int i=0; i<count; i++) {
          System.out.println(">>>"+i);
        }
      }
    }



    new Thread(new MyRunnable()).start();

    for(int i=0; i<count; i++) {
      System.out.println("==>"+i);
    }

  }
}
