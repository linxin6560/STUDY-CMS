package com.shishuo.cms.dao;

import com.shishuo.cms.entity.Album;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 相册
 * Created by Administrator on 2016/10/27.
 */
@Repository
public interface AlbumDao {

    // ///////////////////////////////
    // ///// 增加 ////////
    // ///////////////////////////////

    /**
     * 添加管理员
     *
     * @param album
     * @return Integer
     */
    int addAlbum(Album album);

    // ///////////////////////////////
    // ///// 刪除 ////////
    // ///////////////////////////////

    /**
     * 删除管理员
     *
     * @param id
     * @return Integer
     */
    int deleteAlbum(@Param("id") long id);

    // ///////////////////////////////
    // ///// 修改 ////////
    // ///////////////////////////////

    /**
     * 修改管理员的信息
     *
     * @param title
     * @param cover
     */
    void updateAlbumById(@Param("id") long id, @Param("title") String title, @Param("cover") String cover);

    // ///////////////////////////////
    // ///// 查詢 ////////
    // ///////////////////////////////

    /**
     * 获取所有管理员列表
     *
     * @param offset
     * @param rows
     * @return List<Admin>
     */
    List<Album> getAllList(@Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取所有管理员的数量
     *
     * @return Integer
     */
    int getAllListCount();

    /**
     * 通过Id获得指定管理员资料
     *
     * @param id
     * @return Admin
     */
    Album getAlbumById(@Param("id") long id);
}
