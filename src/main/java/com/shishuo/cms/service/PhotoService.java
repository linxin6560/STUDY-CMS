package com.shishuo.cms.service;

import com.shishuo.cms.dao.PhotoDao;
import com.shishuo.cms.entity.Photo;
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
 * Created by Administrator on 2016/11/23.
 */
@Service
public class PhotoService {

    @Autowired
    private PhotoDao photoDao;

    /**
     * 添加管理员
     *
     * @param albumId
     * @param file
     * @return Album
     */
    public Photo addPhoto(int albumId, MultipartFile file) throws AuthException, IOException {
        String filename = MediaUtils.saveImage(file, 0, 0);
        Date now = new Date();
        Photo photo = new Photo();
        photo.setTitle(file.getOriginalFilename());
        photo.setAlbumId(albumId);
        photo.setFilename(filename);
        photo.setCreateTime(now);
        photoDao.addPhoto(photo);
        return photo;
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
    public int deletePhoto(long id) {
        return photoDao.deletePhoto(id);
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

    public void updatePhotoById(long id, String title, String cover) throws AuthException {
        photoDao.updatePhotoById(id, title, cover);
    }

    // ///////////////////////////////
    // ///// 查詢 ////////
    // ///////////////////////////////

    /**
     * 通过Id获得指定管理员资料
     */
    public Photo getPhotoById(long id) {
        return photoDao.getPhotoById(id);
    }

    /**
     * 获得所有管理员的分页数据
     *
     * @param offset
     * @param rows
     * @return List<Admin>
     */
    public List<Photo> getAllList(int albumId, long offset, long rows) {
        return photoDao.getAllList(albumId, offset, rows);
    }

    /**
     * 获得所有管理员的数量
     *
     * @return Integer
     */
    public int getAllListCount(int albumId) {
        return photoDao.getAllListCount(albumId);
    }

    /**
     * 获得所有管理员的分页
     *
     * @param pageNum
     * @return PageVo<Admin>
     */
    public PageVo<Photo> getAllListPage(int albumId, int pageNum) {
        PageVo<Photo> pageVo = new PageVo<Photo>(pageNum);
        pageVo.setRows(20);
        List<Photo> list = getAllList(albumId, pageVo.getOffset(), pageVo.getRows());
        pageVo.setList(list);
        pageVo.setCount(getAllListCount(albumId));
        return pageVo;
    }
}
