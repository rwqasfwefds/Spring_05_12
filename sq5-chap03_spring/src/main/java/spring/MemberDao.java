package spring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// XXXDao 역할 : 데이터베이스(DB)와 연결해서 CRUD 용도인 클래스
// DB 역할 대신하는 자료구조 : HashMap -> 부모 Map(키, 값) 자료구조
// Member이라는 테이블이 오라클에 있는 쉨히임(지금 여기선 있다고 생각)
// 그러니깐 본래의 기능은 데이터 베이스에 있는 Member라는 테이블에 접근해서 CRUD를 할 수 있게금 만드는 거임
// 근데 지금은 데이터 베이스랑 연결해서 쓰기엔 너무 빡세니깐
// 그냥 Map으로 그 역할을 대신하는 거임
// 배열 vs 맵 차이점 : 배열 arr[0] = 'a' vs 해쉬맵 arr[키] = 'a'

// 스프링 환경설정 파일에 ComponentScan을 적으면 자동으로 Bean객체로 등록을 해주는데
// 어떤 클래스를 등록할 지 지정을 해줘야함
// 그 지정이 그 클래스 위에 @Component 를 걸어주는거임
@Component
public class MemberDao {
    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>();

//    해쉬맵에 자료가 무엇이 있는지 확인하는 메소드 ( Select )
//    기능 : 이메일을 검색하는 메소드
    public Member selectByEmail(String email) {
        return map.get(email);
    }

//    해쉬맵에 자료를 생성하는 메소드( Insert )
//    회원정보를 해쉬맵에 저장하는 메소드( id = 1증가시켜서 )
    public void insert(Member member) {
//        아이디 1 증가시키는 로직( id 중복 방지 )
        member.setId(++nextId);
//        해쉬맵에 회원정보 저장(키, 값) = (이메일, 회원정보 객체)
        map.put(member.getEmail(), member);
    }

//    해쉬맵에 자료를 수정하는 메소드( Update )
    public void update(Member member) {
        map.put(member.getEmail(), member);
    }
}
