package com.example.cxfdemo.controller;

import com.example.cxfdemo.utils.ZipUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试zip文件压缩工具类
 *
 * @author Sean
 * 2019/11/12
 */
@RestController
public class ZipController {

    @GetMapping("/down")
    public void downZip(HttpServletResponse response) {
        String srcPath = "F:\\W3school";
        String fileName = "测试";
        ZipUtils.compressAndDownLoad(srcPath, response, fileName);
    }

    @GetMapping("/exc")
    public void exception(){
        throw new NullPointerException("异常");
    }

}
