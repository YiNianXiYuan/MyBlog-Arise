package com.study.dao;

import com.study.dto.AttachDto;
import com.study.pojo.Attach;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/28 下午11:49
 * @Description: 文件相关Dao接口
 */
@Mapper
public interface AttachDao {
    /**
     * 添加单个附件文件
     * @param attAch
     */
    void addAttAch(Attach attAch);

    /**
     * 获取所有的附件信息
     * @return
     */
    List<AttachDto> getAtts();

    /**
     * 获取附件总数
     * @return
     */
    Long getAttAchCount();

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttachDto getAttAchById(@Param("id") Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(@Param("id") Integer id);
}
