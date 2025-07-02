package hello.hello_spring.web;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller가 있으면 컨트롤러 객체를 spring container에서 생성 저장, 이것을 스프링 빈이 관리된다고 말함
@Controller
public class MemberController {
    // 여러 컨트롤러가 memberSerivce를 가져다 쓸 수 있으니 new로 생성하는건 부적절, 따라서 spring container에 등록하는게 합리적
    private final MemberService memberService;

    // Autowired라고 하면 매개변수 객체를 spring container에서 가져옴, dependency injection
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
