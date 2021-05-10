package study.jpatransactional.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import study.jpatransactional.dao.MemberRepository;
import study.jpatransactional.domain.Member;

@Service
@RequiredArgsConstructor
@Slf4j

/*
@Transactional 의 전파 옵션의 기본값은 REQUIRED


REQUIRED
-> 부모 트랜잭션이 있다면 부모 트랜잭션에 합류합니다.
-> 부모 트랜잭션이 없다면 새로운 트랜잭션을 생성

REQUIRED_NEW
-> 새로운 트렌잭션을 생성합니다. 각각의 트랜잭션이 롤백되더라도 서로 영향을 주지 않는다.

MANDATORY
-> 부모 트랜잭션에 합류합니다. 만약 부모 트랜잭션이 없다면 예외를 발생시킴

NESTED
-> 부모 트랜잭션이 있으면 중첩 트랜잭션 생성
-> 중첩 트랜잭션 안에서 롤백 되더라도 부모 트랜잭션은 영향을 받지않음
-> 단 부모 트랜잭션이 오류 발생시 중첩 트랜잭션이 정상적으로 종료 되어도 모두 롤백
-> 부모 트랜잭션이 없다면 새로운 트랜잭션을 생성
(JPA 는 지원하지 않습니다. 왜 ?  변경감지를 통해서 업데이트문을 최대한 지연해서 발행하는 방식을 사용하기 때문에 중첩된 트랜잭션 경계를 설정할 수 없어 지원하지 않습니다.)

NEVER
-> 트랜잭션을 생성하지 않음 부모 트랜잭션이 존재한다면 예외 발생

SUPPORTS
->트랜잭션이 존재할 경우 해당 트랜잭션을 이용하고 존재하지 않을 경우는 트랜잭션을 이용하지 않음.


*/

public class SimpleService {

    private final MemberRepository memberRepository;
    private final OtherService otherService;

    @Transactional
    public void testMethod() {
        log.info(TransactionSynchronizationManager.getCurrentTransactionName());
        Member kjj = getMember("KJJ");
        memberRepository.save(kjj);
        otherService.save();
//        exceptionMethod();
    }


    private void exceptionMethod(){
        throw new RuntimeException();
    }

    private Member getMember(String name) {
        return Member.builder()
            .name(name)
            .build();
    }
}
