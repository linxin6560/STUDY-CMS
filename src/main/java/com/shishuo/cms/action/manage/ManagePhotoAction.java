package com.shishuo.cms.action.manage;

import com.shishuo.cms.entity.Photo;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.FolderNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 照片
 * Created by Administrator on 2016/11/23.
 */
@Controller
@RequestMapping("/manage/photo")
public class ManagePhotoAction extends ManageBaseAction {

    @RequestMapping(value = "/list.htm", method = RequestMethod.GET)
    public String list(
            @RequestParam(value = "album_id") int albumId,
            @RequestParam(value = "p", defaultValue = "0") int p,
            ModelMap modelMap, HttpServletRequest request)
            throws FolderNotFoundException {
        PageVo<Photo> pageVo = photoService.getAllListPage(albumId, p);
        modelMap.put("photoPage", pageVo);
        return "manage/photo/list";
    }

    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public JsonVo<Photo> add(@RequestParam(value = "album_id") int albumId,
                             @RequestParam(value = "file") MultipartFile file,
                             HttpServletRequest request, ModelMap modelMap) {
        JsonVo<Photo> jsonVo = new JsonVo<Photo>();
        try {
            if (file == null || file.isEmpty()) {
                jsonVo.getErrors().put("fileError", "封面不能为空");
            }
            jsonVo.check();
            jsonVo.setResult(true);
            Photo photo = photoService.addPhoto(albumId, file);
            jsonVo.setT(photo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonVo.setResult(false);
        }
        return jsonVo;
    }
}
