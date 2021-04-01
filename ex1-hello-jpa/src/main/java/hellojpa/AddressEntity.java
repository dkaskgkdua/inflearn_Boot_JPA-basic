package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
public class AddressEntity {
    @Id @GeneratedValue
    private Long id;
    private Address address;
}
