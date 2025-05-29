package top.shusheng007.composite.common.config;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 11:28
 * description:
 */
public class President {
    private String name;
    private int age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "President{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
