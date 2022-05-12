package chap07;

/**
 * packageName : chap07
 * fileName : CommonTest
 * author : ds
 * date : 2022-05-11
 * description : 팩토리얼 공통 클래스 테스트 : 실행 시간이 잘 출력 되는가?
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-11         ds          최초 생성
 */
public class CommonTest {
    public static void main(String[] args) {
//        객체 생성
        ExeTimeCalculator etcalc = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(etcalc.factorial(545614554));
    }
}
