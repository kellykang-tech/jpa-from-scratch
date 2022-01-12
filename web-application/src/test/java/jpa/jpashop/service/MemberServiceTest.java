package jpa.jpashop.service;

import jpa.jpashop.domain.Member;
import jpa.jpashop.repositoty.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Kate");
        // when
        Long saveId = memberService.join(member);
        System.out.println("영속성 컨텍스트에 memberId 저장된 후 memberId = " + saveId);
        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        // given
        final Member member1 = new Member();
        member1.setName("Dorothy");

        final Member member2 = new Member();
        member2.setName("Dorothy");

        // when
        memberService.join(member1);
        memberService.join(member2);    // 같은 회원 중복 가입 시키기

        // then
        fail("중복 회원 예외가 발생해야 합니다.");
    }


}