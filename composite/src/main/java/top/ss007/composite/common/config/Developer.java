package top.ss007.composite.common.config;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 13:26
 * description:
 */
public class Developer {
    private String name;
    private int age;
    private String programLang;

    public Developer(String name, int age, String programLang) {
        this.name = name;
        this.age = age;
        this.programLang = programLang;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", programLang='" + programLang + '\'' +
                '}';
    }
}
