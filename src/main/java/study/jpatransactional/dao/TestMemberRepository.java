package study.jpatransactional.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.domain.TestMember;

public interface TestMemberRepository extends JpaRepository<TestMember, Long> {

}
