package top.ss007.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ss007.mybatisplus.persistence.po.StudentPo;
import top.ss007.mybatisplus.persistence.service.StudentPoService;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/5/15 12:36 下午
 * @description:
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/mp")
public class MpController {
    private final StudentPoService studentPoService;

    @GetMapping("/allStd")
    public Boolean getAllStudents() {
        List<StudentPo> all =  studentPoService.list();
        LambdaQueryWrapper<StudentPo> wpQuery = Wrappers.<StudentPo>lambdaQuery()
                .eq(StudentPo::getStuName, "王婆")
                .le(StudentPo::getAge, 19)
                .or()
                .like(StudentPo::getStuName, "王二狗");
        List<StudentPo> wpStd = studentPoService.list(wpQuery);

        LambdaQueryWrapper<StudentPo> lt19Query = Wrappers.<StudentPo>lambdaQuery()
                .le(StudentPo::getAge, 19)
                .orderByAsc(StudentPo::getAge);

        List<StudentPo> lt19Std = studentPoService.list(lt19Query);

        studentPoService.update(Wrappers.<StudentPo>lambdaUpdate()
                .eq(StudentPo::getStuName,"kim")
                .set(StudentPo::getStuName,"ss007"));

        studentPoService.save(StudentPo.builder()
                .stuName("宋江")
                .age(50)
                .build());
        return true;
    }
}
