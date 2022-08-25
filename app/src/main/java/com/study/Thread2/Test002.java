// 1) 스레드 사용 전
// 2) 스레드 사용 후 : Runnable 구현체를 패키지 멤버로 만들어 Thread 실행한다.
//    Runnable(인터페이스) 사용
package com.study.Thread2;

public class Test002 {
  public static void main(String[] args) {
    int count = 10;

    // step1)
    //    MyRunnable r = new MyRunnable(count);
    //    Thread t =new Thread();
    //    t.start();

    // step2)
    //    Thread t = new Thread(new MyRunnable(count));
    //    t.start();

    // step3)
    new Thread(new MyRunnable(count)).start();

    for(int i=0; i<count; i++) {
      System.out.println("==>"+i);
    }

  }
}
