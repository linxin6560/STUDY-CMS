package com.shishuo.cms.action.manage;

import com.shishuo.cms.entity.Album;
import com.shishuo.cms.entity.Photo;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.ArticleNotFoundException;
import com.shishuo.cms.exception.FolderNotFoundException;
import com.shishuo.cms.util.MediaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 相册Action
 * Created by Administrator on 2016/10/27.
 */
@Controller
@RequestMapping("/manage/album")
public class ManageAlbumAction extends ManageBaseAction {

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
                             @RequestParam(value = "cover") MultipartFile file,
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

    /**
     * @throws Exception
     * @author 进入修改文章页面
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.GET)
    public String update(
            @RequestParam(value = "albumId") long albumId,
            ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Album album = albumService.getAlbumById(albumId);
        modelMap.put("album", album);
        return "manage/album/update";
    }

    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public JsonVo<Album> update(@RequestParam(value = "albumId") long albumId,
                                @RequestParam(value = "title") String title,
                                @RequestParam(value = "pic", required = false) String cover,
                                HttpServletRequest request, ModelMap modelMap) {
        JsonVo<Album> jsonVo = new JsonVo<Album>();
        try {
            if (StringUtils.isEmpty(title)) {
                jsonVo.getErrors().put("titleError", "标题不能为空");
            }
            System.out.println("title=" + title + ",cover=" + cover);
            jsonVo.check();
            jsonVo.setResult(true);
            Album album = albumService.updateAlbumById(albumId, title, cover);
            jsonVo.setT(album);
        } catch (Exception e) {
            e.printStackTrace();
            jsonVo.setResult(false);
        }
        return jsonVo;
    }

    /**
     * @throws ArticleNotFoundException
     * @author 彻底删除文件
     */
    @ResponseBody
    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    public JsonVo<String> deleteFile(@RequestParam(value = "albumId") int albumId)
            throws ArticleNotFoundException {
        JsonVo<String> json = new JsonVo<String>();
        // 删除文件系统
        albumService.deleteAlbum(albumId);
        List<Photo> photoList = photoService.getAllList(albumId, 1000, 1);
        for (Photo photo : photoList) {
            MediaUtils.deleteFile(photo.getFilename());
        }
        json.setResult(true);
        return json;
    }
}
