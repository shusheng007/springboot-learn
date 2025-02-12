package top.ss007.readwritesplit.demo.persistence.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName student
 */
@Data
public class Student implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}