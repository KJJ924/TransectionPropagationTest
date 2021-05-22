package study.jpatransactional.propagation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.propagation.domain.TestMember;

public interface TestMemberRepository extends JpaRepository<TestMember, Long> {

}
