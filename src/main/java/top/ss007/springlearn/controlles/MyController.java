package top.ss007.springlearn.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ss007.springlearn.model.UserModel;
import top.ss007.springlearn.service.FileService;
import top.ss007.springlearn.utils.Base64ToMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/filter")
    public String testFilter(@RequestParam(name = "name", required = false) String name) {
        return String.valueOf(name);
    }

    @PostMapping(value = "/uploadFile")
    public Boolean uploadFile(@RequestBody Map<String, String> request) {
        String[] base64Array = request.get("base64").split(",");
        String dataUir, data;
        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            //根据你base64代表的具体文件构建
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }
        MultipartFile multipartFile = new Base64ToMultipartFile(data, dataUir);
        fileService.uploadFile(multipartFile);
        return true;
    }


}
