// 1) 스레드 사용 전
package com.study.Thread2;

public class Test001 {
  public static void main(String[] args) {
    int count = 10;

    for(int i=0; i<count; i++) {
      System.out.println(">>>"+i);
    }
    for(int i=0; i<count; i++) {
      System.out.println("==>"+i);
    }

  }
}
