package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // persistence.xml에 있는 유닛 정보를 넣음
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        /**
         *  - 엔티티매니저팩토리는 하나만 생성해서 어플리케이션 전체에서 공유함.
         *  - 엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 함.)
         *  - JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
         */
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("Hello A");
            em.persist(member);



            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());

            findMember.setName("Hello B");

            Member findMember2 = em.find(Member.class, 1L);
            System.out.println(findMember2.getName());
            /**
             * JPQL이라는 객체 지향 쿼리 언어 제공함
             * 엔티티 객체를 대상으로 쿼리 구성
             */
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)  // 페이징 시작
                    .setMaxResults(5)   // 페이징 끝
                    .getResultList();
            for (Member item : result) {
                System.out.println("member : " + item.getName());
            }

            em.remove(findMember2);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
