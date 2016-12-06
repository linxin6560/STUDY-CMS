package com.shishuo.cms.action.api;

import com.shishuo.cms.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 接口基类
 * Created by Administrator on 2016/12/6.
 */
@Controller
public class ApiBaseAction {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    protected ConfigService configService;

    @Autowired
    protected FolderService folderService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected MediaService attachmentService;

    @Autowired
    protected AdminService adminService;

    @Autowired
    protected HeadlineService headlineService;

    @Autowired
    protected AdminFolderService adminFolderService;

    @Autowired
    protected AlbumService albumService;

    @Autowired
    protected PhotoService photoService;

    @Autowired
    protected CommonService commonService;
}
