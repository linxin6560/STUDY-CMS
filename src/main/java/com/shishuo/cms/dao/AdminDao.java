/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.Admin;
import com.shishuo.cms.entity.vo.AdminVo;

/**
 * 管理员
 *
 * @author Zhangjiale
 */

@Repository
public interface AdminDao {

    // ///////////////////////////////
    // ///// 增加 ////////
    // ///////////////////////////////

    /**
     * 添加管理员
     *
     * @param admin
     * @return Integer
     */
    int addAdmin(Admin admin);

    // ///////////////////////////////
    // ///// 刪除 ////////
    // ///////////////////////////////

    /**
     * 删除管理员
     *
     * @param adminId
     * @return Integer
     */
    int deleteAdmin(@Param("adminId") long adminId);

    // ///////////////////////////////
    // ///// 修改 ////////
    // ///////////////////////////////

    /**
     * 修改管理员的信息
     *
     * @param adminId
     * @param password
     */
    void updateAdminByadminId(@Param("adminId") long adminId, @Param("password") String password);

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
    List<Admin> getAllList(@Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取所有管理员的数量
     *
     * @return Integer
     */
    int getAllListCount();

    /**
     * 通过Id获得指定管理员资料
     *
     * @param adminId
     * @return Admin
     */
    Admin getAdminById(@Param("adminId") long adminId);

    /**
     * 通过email获得指定的管理员
     *
     * @param name
     * @return Admin
     */
    AdminVo getAdminByName(@Param("name") String name);

}
