package study.jpatransactional.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.support.TransactionSynchronizationManager;
import study.jpatransactional.dao.TestMemberRepository;
import study.jpatransactional.domain.TestMember;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtherService {

    private final TestMemberRepository memberRepository;


    @Transactional(propagation = Propagation.NESTED)
    public void save() {
        log.info(TransactionSynchronizationManager.getCurrentTransactionName());
        memberRepository.save(getMember("백수"));
        exceptionMethod();

    }

    private TestMember getMember(String status) {
        return TestMember.builder().status(status).build();
    }

    private void exceptionMethod() {
        throw new RuntimeException();
    }
}
