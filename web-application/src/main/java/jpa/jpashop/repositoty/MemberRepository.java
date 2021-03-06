package jpa.jpashop.repositoty;

import jpa.jpashop.domain.Member;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 쿼리 캐시
    public List<Member> findAllWithCache() {
        return em.createQuery("select m from Member m", Member.class)
                            .setHint("org.hibernate.cacheable", true)
                            .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // 어떤 SQL 문이 날아가는지 보기 위함
    public void flush() {
        em.flush();
    }

}
