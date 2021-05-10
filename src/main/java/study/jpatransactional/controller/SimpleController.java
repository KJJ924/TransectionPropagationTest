package study.jpatransactional.controller;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.jpatransactional.dao.MemberRepository;
import study.jpatransactional.dao.TestMemberRepository;
import study.jpatransactional.domain.Member;
import study.jpatransactional.domain.TestMember;
import study.jpatransactional.service.SimpleService;

@RestController
@RequiredArgsConstructor
public class SimpleController {

    private final MemberRepository memberRepository;
    private final TestMemberRepository testMemberRepository;
    private final SimpleService simpleService;

    @GetMapping("/test")
    public void test(){
        simpleService.testMethod();
    }

    @GetMapping("/members")
    public ResponseEntity<Holder> test2(){
        List<Member> all = memberRepository.findAll();
        List<TestMember> members = testMemberRepository.findAll();
        return ResponseEntity.ok(Holder.of(all,members));
    }

    @Getter
    @Setter
    private static class Holder{
        private List<Member> members;
        private List<TestMember> testMembers;

        private Holder(List<Member> members,
            List<TestMember> testMembers) {
            this.members = members;
            this.testMembers = testMembers;
        }

        public static Holder of(List<Member> members,
            List<TestMember> testMembers){
            return new Holder(members,testMembers);
        }
    }
}
