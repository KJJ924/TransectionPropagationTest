package study.jpatransactional.propagation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.propagation.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
