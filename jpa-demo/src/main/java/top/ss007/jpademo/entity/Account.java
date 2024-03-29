package top.ss007.jpademo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "account", schema = "jpa-learn")
public class Account extends AbstractAuditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    private Student student;

    @Version
    private Long version;
}
