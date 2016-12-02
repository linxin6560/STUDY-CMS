package com.shishuo.cms.service;

import com.shishuo.cms.dao.AlbumDao;
import com.shishuo.cms.entity.Album;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.AuthException;
import com.shishuo.cms.util.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 相册
 * Created by Administrator on 2016/10/27.
 */
@Service
public class AlbumService {

    @Autowired
    private AlbumDao albumDao;

    /**
     * 添加管理员
     *
     * @param title
     * @param file
     * @return Album
     */
    public Album addAlbum(String title, MultipartFile file) throws AuthException, IOException {
        String cover = MediaUtils.saveImage(file, 0, 0);
        Date now = new Date();
        Album album = new Album();
        album.setTitle(title);
        album.setCover(cover);
        album.setCreateTime(now);
        albumDao.addAlbum(album);
        return album;
    }

    // ///////////////////////////////
    // ///// 刪除 ////////
    // ///////////////////////////////

    /**
     * 删除管理员
     *
     * @param id
     * @return Integer
     */
    public int deleteAlbum(long id) {
        return albumDao.deleteAlbum(id);
    }

    // ///////////////////////////////
    // ///// 修改 ////////
    // ///////////////////////////////

    /**
     * 修改管理员资料
     *
     * @param id
     * @param title
     * @throws AuthException
     */

    public Album updateAlbumById(long id, String title, String cover) throws AuthException, IOException {
        Album album = albumDao.getAlbumById(id);
        if (cover != null) {
            album.setCover(cover);
        }
        album.setTitle(title);
        albumDao.updateAlbumById(album);
        return album;
    }

    // ///////////////////////////////
    // ///// 查詢 ////////
    // ///////////////////////////////

    /**
     * 通过Id获得指定管理员资料
     */
    public Album getAlbumById(long id) {
        return albumDao.getAlbumById(id);
    }

    /**
     * 获得所有管理员的分页数据
     *
     * @param offset
     * @param rows
     * @return List<Admin>
     */
    public List<Album> getAllList(long offset, long rows) {
        return albumDao.getAllList(offset, rows);
    }

    /**
     * 获得所有管理员的数量
     *
     * @return Integer
     */
    public int getAllListCount() {
        return albumDao.getAllListCount();
    }

    /**
     * 获得所有管理员的分页
     *
     * @param pageNum
     * @return PageVo<Admin>
     */
    public PageVo<Album> getAllListPage(int pageNum) {
        PageVo<Album> pageVo = new PageVo<Album>(pageNum);
        pageVo.setRows(20);
        List<Album> list = this.getAllList(pageVo.getOffset(), pageVo.getRows());
        pageVo.setList(list);
        pageVo.setCount(this.getAllListCount());
        return pageVo;
    }
}
