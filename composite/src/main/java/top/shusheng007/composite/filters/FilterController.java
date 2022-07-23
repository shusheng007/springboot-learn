package top.shusheng007.composite.filters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/5/14 2:22 下午
 * @description:
 */
@RestController
@RequestMapping("/filter")
public class FilterController {

    @GetMapping(value = "/test-filter")
    public String testFilter(@RequestParam(name = "name", required = false) String name) {
        return String.valueOf(name);
    }


}
