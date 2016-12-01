package com.shishuo.cms.action.manage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/common")
public class CommonAction extends ManageBaseAction {
    /***
     * <p>功能描述：上传图片</p>
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        System.out.println("file="+file);
        return commonService.upload(file);
    }
}
