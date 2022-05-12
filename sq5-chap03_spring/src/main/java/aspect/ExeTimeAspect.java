package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * packageName : aspect
 * fileName : ExeTimeAspect
 * author : ds
 * date : 2022-05-12
 * description : 스프링 공통 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-12         ds          최초 생성
 */
// @Aspect : 공통 클래스를 등록하는 어노테이션
@Aspect
public class ExeTimeAspect {

//    빈(내용 없는) 메소드
//    @Pointcut : 공통 메소드 measure()가 적용 될 위치(대상)
//    매개변수 : execution 실행
//    execution(수식어(public) 리턴타입(*) 클래스이름(chap07)메소드이름(매개변수이름) )
//    리턴타입이 * 면 어떤 타입도 올 수 있다 라는 뜻
//    .. 은 0개이상 올 수 있다(아무 메소드이름 올 수 있다)
//    * : 모든 값
//    결론은 chap07안에 있는 클래스의 메소드들을 다 적용 하겠다 라는 뜻
//    어디다가 걸어주고 싶은지
    @Pointcut("execution(public * chap07..*(..))")
    private void publicTarget(){

    }
    
//    공통 메소드를 언제 호출 할 지 나타내는 어노테이션을 달기
//    시작 시간 공통 함수
//    업무로직 호출(팩토리얼 메소드)
//    끝 시간 공통 함수
//    언제 걸어주고 싶은지
//    스프링 AOP(공통 클래스 사용) : 내부적으로 프록시 패턴 사용
//    프록시 : 진짜를 대신해서 가짜가 진짜 행세를 하는 클래스
//    프록시 쓰는 이유 : 1. 진짜 클래스나 메소드 이름을 노출 시키기 싫어서(보안)
//                    2. 진짜의 일부기능을 미리 사용 할 필요가 있을때
//    스프링 프록시 : ProceedingJoinPoint
//    스프링에서는 프록시를 미리 쓰라고 만들어 놨는데 그게 저 위에거임
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
//        나노시간 측정 시간
        long start = System.nanoTime();
        try {
//            proceed() : 진짜 업무 메소드를 찾아서 호출
//            진짜 업무 메소드 : 팩토리얼 계산 메소드
            Object result = joinPoint.proceed();
            return result;
        } finally {
//            나노시간 측정 끝
            long end = System.nanoTime();
            System.out.println("(스프링 공통함수)실행시간 : " + (end - start));
        }
    }
}
