package top.shusheng007.composite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 13:40
 * description:
 */


@ConfigurationProperties(prefix = "school.president", ignoreInvalidFields = true)
@ConstructorBinding
public class ImmutablePresident {

    private final String name;
    private final int age;
    private final String gender;

    public ImmutablePresident(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "ImmutablePresident{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
