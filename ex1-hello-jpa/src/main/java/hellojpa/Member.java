package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "USER")
public class Member {
    @Id
    private Long id;

    @Column(name = "name", insertable = true, updatable = true
            , nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * 시간 관련된 매핑 정보 세팅
     * Date : 날짜
     * Time : 시간
     * TIMESTAMP : 날짜시간
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * 어노테이션 없어도 됨됨
    */
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    public Member() {
    }

}
