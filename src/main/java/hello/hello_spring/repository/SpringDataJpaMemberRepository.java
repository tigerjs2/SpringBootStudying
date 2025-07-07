package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ? 으로 JPQL이 자동으로 작성됨
    @Override
    Optional<Member> findByName(String name);
    // findByNameAAndId(String name, Long id) 도 자동으로 JPQL 생성됨
}
