package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
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
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street","zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "stold1","oldzip1"));
            member.getAddressHistory().add(new Address("old2", "stold2","oldzip2"));

            em.persist(member);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
