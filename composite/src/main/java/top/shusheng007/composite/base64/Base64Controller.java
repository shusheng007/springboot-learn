package top.shusheng007.composite.base64;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.shusheng007.composite.base64.model.entity.Base64Req;
import top.shusheng007.composite.common.service.FileService;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/5/14 2:27 下午
 * @description:
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/base64")
public class Base64Controller {

    private final FileService fileService;

    @PostMapping(value = "/uploadFile")
    public Boolean uploadFile(@RequestBody Base64Req request) {
        String[] base64Array = request.getBase64().split(",");
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
