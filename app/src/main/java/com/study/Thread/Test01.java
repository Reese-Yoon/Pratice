// 1) 스레드 사용 전
package com.study.Thread;

public class Test01 {
  public static void main(String[] args) {
    int count = 100;

    for(int i=0; i<count; i++) {
      System.out.println("==>" + i);
    }

    for(int i=0; i<count; i++ ) {
      System.out.println(">>>"+i);
    }

  }

}
