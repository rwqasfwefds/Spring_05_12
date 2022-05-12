package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// XXXService : 패스워드를 변경하는 서비스
// Service : 비지니스(업무) 로직을 처리하는 클래스
// Dao + Model(Member, RegisterMember 클래스) 활용해서 업무로직을 짬(코딩)

// 스프링 환경설정 파일에 ComponentScan을 적으면 자동으로 Bean객체로 등록을 해주는데
// 어떤 클래스를 등록할 지 지정을 해줘야함
// 그 지정이 그 클래스 위에 @Component 를 걸어주는거임(ComponentScan 대상이다 라는 표시)
@Component
public class ChangePasswordService {
//    Dao 맴버 변수로 등록 : CRUD를 위해서
//    스프링에 매개변수를 이용해서 객체를 전달해서 생성 : DI
//    @Autowired : 자동으로 DI 코딩부분을 생성해줌
    @Autowired
    private MemberDao memberDao;

//    changePassword 메소드 : 패스워드를 변경하는 서비스
    public void changePassword(String email, String oldPwd, String newPwd) {
//        selectByEmail 메소드 : 이메일로 회원정보를 검색
//        회원정보가 있으면 member 값이 null이 아님
//        회원정보가 없으면 member 값이 null 임
        Member member = memberDao.selectByEmail(email);
//        member가 null이면 회원정보가 없는것임
//        그러므로 패스워드를 변경 할 수 없으므로 에러를 발생시키고 메소드를 빠져나옴
        if(member == null) {
            throw new MemberNotFoundException();
        }

//        에러가 없으면(member가 null이 아니면) 회원정보가 있으므로
//        패스워드 변경 서비스 실행
        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
