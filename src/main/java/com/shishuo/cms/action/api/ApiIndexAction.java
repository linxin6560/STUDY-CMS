package com.shishuo.cms.action.api;

import com.shishuo.cms.entity.vo.ArticleVo;
import com.shishuo.cms.entity.vo.FolderVo;
import com.shishuo.cms.exception.FolderNotFoundException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 接口
 * Created by Administrator on 2016/12/6.
 */
@Controller
@RequestMapping("/api")
public class ApiIndexAction extends ApiBaseAction {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @ResponseBody
    @RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
    public String getIndex(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize", required = false) int pageSize) throws FolderNotFoundException {
        JSONObject object = new JSONObject();
        if (page == 1) {
            List<FolderVo> folderList = folderService.getAllFolderList(0);
            object.put("folder", folderList);
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        List<ArticleVo> articleList = articleService.getAllActicle(page, pageSize);
        object.put("article", articleList);
        return object.toString();
    }
}
