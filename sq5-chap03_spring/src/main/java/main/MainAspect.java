package main;

import chap07.Calculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * packageName : main
 * fileName : MainAspect
 * author : ds
 * date : 2022-05-12
 * description : 공통 클래스(나노시간 측정) 테스트
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-12         ds          최초 생성
 */
public class MainAspect {
    public static void main(String[] args) {
//        스프링의 객체 생성
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long fiveFact = cal.factorial(5);

        System.out.println("factorial(5) : " + fiveFact);

        ctx.close(); // 스프링 종료
    }
}
