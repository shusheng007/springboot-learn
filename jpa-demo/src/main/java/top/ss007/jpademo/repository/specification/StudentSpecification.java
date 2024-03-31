package top.ss007.jpademo.repository.specification;

import cn.hutool.core.util.StrUtil;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import top.ss007.jpademo.entity.Student;
import top.ss007.jpademo.entity.Student_;
import top.ss007.jpademo.entity.Teacher;
import top.ss007.jpademo.entity.Teacher_;

@UtilityClass
public class StudentSpecification {
    public static Specification<Student> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Student_.name), StrUtil.format("%{}%", name));
    }

    public static Specification<Student> hasNumber(String number) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Student_.number), number);
    }

    public static Specification<Student> ageBetween(Integer minAge, Integer maxAge) {
        return (root, query, criteriaBuilder) -> {
            Predicate between = criteriaBuilder.between(root.get(Student_.age), minAge, maxAge);
            query.orderBy(criteriaBuilder.desc(root.get(Student_.age)));
            return between;
        };
    }

    public static Specification<Student> hasTeacher(String teacher) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, Teacher> stuTeachers = root.join(Student_.teachers);
            return criteriaBuilder.equal(stuTeachers.get(Teacher_.name), teacher);
        };
    }


}
