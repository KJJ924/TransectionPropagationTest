package study.jpatransactional.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
