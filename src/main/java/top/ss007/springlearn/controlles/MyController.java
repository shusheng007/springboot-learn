package top.ss007.springlearn.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ss007.springlearn.config.InfoConfig;
import top.ss007.springlearn.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/11 16:52
 * description:
 */
@RestController()
public class MyController {
    private final UserModel userModel;

    @Autowired
    public MyController(UserModel userModel) {
        this.userModel = userModel;
    }

    @GetMapping(value = "/name")
    public List<String> getUserName() {
        List<String> list = new ArrayList<>();
        list.add(userModel.getUserName());
        list.add(userModel.getUserNameByEnv());
        list.add(userModel.getDatabaseInfo());
        list.add(userModel.getComplexConfig());
        list.add(userModel.getConversionConfig());
        list.add(userModel.getImmutablePresident());
        return list;
    }

    @GetMapping(value = "/check")
    public String isSameObj(){
        return userModel.isSameObj();
    }

    @GetMapping(value = "/filter")
    public String testFilter(@RequestParam(name = "name",required = false)String name){
        return String.valueOf(name);
    }


}
