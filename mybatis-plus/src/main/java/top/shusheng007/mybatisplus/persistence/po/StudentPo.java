package top.shusheng007.mybatisplus.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 *
 * @TableName student
 */
@TableName(value = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "stu_name")
    private String stuName;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}