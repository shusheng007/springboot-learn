package top.ss007.composite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 11:00
 * description:
 */
@ConfigurationProperties(prefix = "school",ignoreInvalidFields = true)
public class ComplexConfig {

    private String name;
    private String address;
    private List<String> xiaoQu;
    private Map<String,Integer>department;
    private President president;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getXiaoQu() {
        return xiaoQu;
    }

    public void setXiaoQu(List<String> xiaoQu) {
        this.xiaoQu = xiaoQu;
    }

    public Map<String, Integer> getDepartment() {
        return department;
    }

    public void setDepartment(Map<String, Integer> department) {
        this.department = department;
    }

    public President getPresident() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }

    @Override
    public String toString() {
        return "ComplexConfig{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", xiaoQu=" + xiaoQu +
                ", department=" + department +
                ", president=" + president +
                '}';
    }
}
