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
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람ㅇㅇ");
            movie.setPrice(1000);
            movie.setStockQuantity(1);

            em.persist(movie);

            em.flush();
            em.clear();
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
