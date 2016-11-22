package com.shishuo.cms.action.manage;

import com.shishuo.cms.entity.Album;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.FolderNotFoundException;
import com.shishuo.cms.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 相册Action
 * Created by Administrator on 2016/10/27.
 */
@Controller
@RequestMapping("/manage/album")
public class ManageAlbumAction extends ManageBaseAction {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/list.htm", method = RequestMethod.GET)
    public String list(
            @RequestParam(value = "p", defaultValue = "0") int p,
            ModelMap modelMap, HttpServletRequest request)
            throws FolderNotFoundException {
        PageVo<Album> pageVo = albumService.getAllListPage(p);
        modelMap.put("albumPage", pageVo);
        return "manage/album/list";
    }

    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public String add(ModelMap modelMap, HttpServletRequest request) {
        return "manage/album/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public JsonVo<Album> add(@RequestParam(value = "title") String title,
                             @RequestParam(value = "file") MultipartFile file,
                             HttpServletRequest request, ModelMap modelMap) {
        JsonVo<Album> jsonVo = new JsonVo<Album>();
        try {
            if (StringUtils.isEmpty(title)) {
                jsonVo.getErrors().put("titleError", "标题不能为空");
            }
            if (file == null || file.isEmpty()) {
                jsonVo.getErrors().put("fileError", "封面不能为空");
            }
            jsonVo.check();
            jsonVo.setResult(true);
            Album album = albumService.addAlbum(title, file);
            jsonVo.setT(album);
        } catch (Exception e) {
            e.printStackTrace();
            jsonVo.setResult(false);
        }
        return jsonVo;
    }
}
