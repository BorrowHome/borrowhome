package top.forcebing.borrowhome.common.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.forcebing.borrowhome.common.repository.FileRepository;

import javax.servlet.MultipartConfigElement;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/9  23:31
 */
@RestController
@Api(value = "文件上传接口(已废弃)", tags = "文件上传")
@Slf4j
@Deprecated
public class FileUploadController {


    @Autowired
    private FileRepository fileRepository;


    @Value("${file.temp}")
    private String tmpLocation;
    @Value(("${file.resourceLocation}"))
    private String resourceLocation;

    @Bean
    public MultipartConfigElement multipartConfigElement(
            @Value("${multipart.maxFileSize}") String maxFileSize,
            @Value("${multipart.maxRequestSize}") String maxRequestSize) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(maxFileSize);
        // 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }

    @RequestMapping(value = "/file", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpdate(@RequestParam(value = "file") MultipartFile file) throws Exception {

        return null;
    }

}
