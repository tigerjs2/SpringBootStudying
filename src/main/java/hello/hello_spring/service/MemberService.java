package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// spring container에 자동 등록
// component scan 방식, @Component 포함된 애노테이션 자동 의존관걔 추가
// 애노테이션은 Application이 포함된 패키지에서만 스캔
// @Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // dependency injection: 서비스 객체가 자신이 의존하는 리포지토리 객체의 구현체를 직접 생성하지 않고 외부에서 전달
    // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /*
     *  회원 가입
     */
    public Long join(Member member){
        // 중복 이름 불가
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

}
