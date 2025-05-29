package top.shusheng007.jpademo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "student", schema = "jpa-learn")
public class Student extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "stu_name")
    private String name;

    @Column(name = "stu_number")
    private String number;

    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "student_teacher_relation",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;


}
