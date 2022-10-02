package top.shusheng007.readwritesplit.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 12:06
 * @description:
 */
@Data
public class StudentReq {
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
