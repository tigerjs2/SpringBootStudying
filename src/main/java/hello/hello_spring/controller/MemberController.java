package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping("members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
