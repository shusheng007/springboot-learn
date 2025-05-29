package top.shusheng007.usefaulltools.model;

import lombok.Data;

import java.util.Date;

@Data
public class Programer {
    private String name;
    private String lang;
    private Double height;
    private Date beDate;
    private Address address;
    private String girlName;
    private String girlDes;
}
