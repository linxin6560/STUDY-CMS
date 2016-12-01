package com.shishuo.cms.action.manage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/common")
public class CommonAction extends ManageBaseAction {
    /***
     * <p>功能描述：上传图片</p>
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        System.out.println("common....upload");
        return commonService.upload(request, response);
    }
}
