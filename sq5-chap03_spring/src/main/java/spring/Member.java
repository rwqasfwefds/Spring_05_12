package spring;

import java.time.LocalDateTime;

/**
 * packageName : spring
 * fileName : Member
 * author : ds
 * date : 2022-05-11
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-11         ds          최초 생성
 */
public class Member {
    private Long id; // 회원 아뒤
    private String email; // 회원 이메일
    private String password; // 회원 비번
    private String name; // 회원 이름
    private LocalDateTime registerDateTime; // 회원 가입 날짜

    //    id를 뺀 매개변수 4개짜리 생성자
    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    //    패스워드 변경하는 매소드
//    메소드 : 이전 패스워드, 바뀔 패스워드
    public void changePassword(String oldPassword, String newPassword){
//        현재 멤버변수 패스워드 != 매개변수의 이전 패스워드 이면 if문이 실행
        if (!password.equals(oldPassword)){
//            WrongIdPasswordException : 개발자가 직접 만든 에러 처리용 클래스
//            throw : 사용자 정의 에러 클래스로 강제 에러 발생
            throw new WrongIdPasswordException();
        }
//        새로운 패스워드로 변경
        this.password = newPassword;
    }
}
