package com.shishuo.cms.dao;

import com.shishuo.cms.entity.Photo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 照片Dao
 * Created by Administrator on 2016/11/23.
 */
@Repository
public interface PhotoDao {
    // ///////////////////////////////
    // ///// 增加 ////////
    // ///////////////////////////////

    /**
     * 添加照片
     *
     * @param photo
     * @return Integer
     */
    int addPhoto(Photo photo);

    // ///////////////////////////////
    // ///// 刪除 ////////
    // ///////////////////////////////

    /**
     * 删除照片
     *
     * @param id
     * @return Integer
     */
    int deletePhoto(@Param("id") long id);

    // ///////////////////////////////
    // ///// 修改 ////////
    // ///////////////////////////////

    /**
     * 修改照片的信息
     *
     * @param title
     * @param cover
     */
    void updatePhotoById(@Param("id") long id, @Param("title") String title, @Param("cover") String cover);

    // ///////////////////////////////
    // ///// 查詢 ////////
    // ///////////////////////////////

    /**
     * 获取所有照片列表
     *
     * @param offset
     * @param rows
     * @return List<Admin>
     */
    List<Photo> getAllList(@Param("albumId") long albumId, @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取所有照片的数量
     *
     * @return Integer
     */
    int getAllListCount(@Param("albumId") long albumId);

    /**
     * 通过Id获得指定照片资料
     *
     * @param id
     * @return Admin
     */
    Photo getPhotoById(@Param("id") long id);
}
