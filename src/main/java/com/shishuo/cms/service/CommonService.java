package com.shishuo.cms.service;

import com.shishuo.cms.util.MediaUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.UUID;

/***
 *
 * <p>文件名称：Common_Service.java</p>
 * <p>文件描述：公共部分相关操作</p>
 * <p>版权所有： 版权所有(C)2013-2099</p>
 * <p>公   司： 每美 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 *
 * @version 1.0
 * @author <a> href="mailto:guopengjie@vmei.me">guopengjie</a>
 * @since 2015年12月30日 上午11:42:06
 */
@Service
public class CommonService {
    private static Logger log = Logger.getLogger(CommonService.class.getClass());

    /***
     *
     * <p>功能描述：上传图片</p>
     * <p>创建人：guopengjie</p>
     * <p>创建日期：2015年12月30日 上午11:39:28</p>
     *
     * @param request
     * @param response
     * @return
     */
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String result = "";
        try {
            Iterator<String> itr = request.getFileNames();
            MultipartFile file;
            while (itr.hasNext()) {
                file = request.getFile(itr.next());
                String fileName = MediaUtils.save(file);
                jsonObject.put("fileName", fileName);
            }
        } catch (Exception e) {
            log.error("上传图片异常", e);
            jsonObject.put("e", e.getMessage());
        }
        result = jsonObject.toString();
        System.out.println("result="+result);
        return result;
    }
}
