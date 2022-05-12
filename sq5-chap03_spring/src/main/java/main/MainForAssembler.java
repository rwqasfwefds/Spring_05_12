package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName : assembler
 * fileName : MainForAssembler
 * author : TaeGyung
 * date : 2022-04-30
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-04-30         TaeGyung          최초 생성
 */
public class MainForAssembler {
//    Assembler = 객체를 생성 : Member 객체, 패스워드 변경 서비스 객체, 회원등록 객체 생성(이 역할을 스프링이 함)
//    private static Assembler assembler = new Assembler();
    
//    스프링 객체
//    assembler 가 했던 객체 생성 일을 이제 스프링이 함
    private static AnnotationConfigApplicationContext ctx = null;

    private static void processNewCommand(String[] arg) {
        if(arg.length != 5) {
            printHelp();
            return;
        }

//        스프링에 객체를 요청 : MemberRegisterService라는 객체
//        스프링 환경설정 하는 곳에 Bean부분을 다 주석처리 했으니깐 함수 이름을 못 씀
//        그래서 그냥 이름 빼면 됨
//        뭐 ㅅㅂ 오버로딩 이라고 하는데 그건 모르겠고 시발
//        MemberRegisterService reqSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
        MemberRegisterService reqSvc = ctx.getBean(MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if(!req.isPasswordEqualToConfiremPassword()) {
            System.out.println("암호오 혹인이 일치하지 않습니다.\n");
        }

        try {
            reqSvc.regist(req);
            System.out.println("등록했습니다.");
        } catch(DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다\n");
        }

    }

    private static void processChangeCommand(String[] arg) {
        if(arg.length != 4) {
            printHelp();
            return;
        }

//        스프링에 객체를 요청 : ChangePasswordService라는 객체
//        ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
        ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch(MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch(WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령어입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        
//        스프링 생성하는 로직 실행
//        매개변수로 스프링 환경설정을 한 파일을 넘겨줘야 함
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        
        BufferedReader reader = 
                new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            System.out.println("명령어를 입력하세요");
//            콘솔에서 입력을 한줄 받음
            String command = reader.readLine();

//            exit 이면 종료
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
//            new + " " 이면 processNewCommand 실행
            if(command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
//                change + " " 이면 processNewCommand 실행
            } else if(command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }
        }
        printHelp();
    }
}
