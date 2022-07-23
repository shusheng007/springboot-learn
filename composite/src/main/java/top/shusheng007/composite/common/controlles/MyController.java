package top.shusheng007.composite.common.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.composite.common.service.FileService;
import top.shusheng007.composite.common.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/11 16:52
 * description:
 */

@RestController
@RequestMapping("")
public class MyController {
    private final UserModel userModel;
    private final FileService fileService;

    @Autowired
    public MyController(UserModel userModel, FileService fileService) {
        this.userModel = userModel;
        this.fileService = fileService;
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
    public String isSameObj() {
        return userModel.isSameObj();
    }






}
