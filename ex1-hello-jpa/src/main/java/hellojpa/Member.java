package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", insertable = true, updatable = true
            , nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private String city;
    private String street;
    private String zipcode;

//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    /**
//     * 시간 관련된 매핑 정보 세팅
//     * Date : 날짜
//     * Time : 시간
//     * TIMESTAMP : 날짜시간
//     */
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    /**
//     * 어노테이션 없어도 됨됨
//    */
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob
//    private String description;
//
//    public Member() {
//    }

}
