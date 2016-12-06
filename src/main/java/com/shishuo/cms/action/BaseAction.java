/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.shishuo.cms.action;

import com.shishuo.cms.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Herbert
 */
public class BaseAction {

    @Autowired
    protected FolderService folderService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected TemplateService themeService;

    @Autowired
    protected HeadlineService headlineService;

    @Autowired
    protected AlbumService albumService;

    @Autowired
    protected PhotoService photoService;

    protected final Logger logger = Logger.getLogger(this.getClass());
}
