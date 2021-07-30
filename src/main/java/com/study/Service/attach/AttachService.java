package com.study.Service.attach;

import com.github.pagehelper.PageInfo;
import com.study.dto.AttachDto;
import com.study.pojo.Attach;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午9:53
 * @Description: 文件相关接口
 */
public interface AttachService {
    /**
     * 添加单个附件信息
     * @param attAchDomain
     */
    void addAttAch(Attach attAchDomain);

    /**
     * 获取所有的附件信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AttachDto> getAtts(int pageNum, int pageSize);

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttachDto getAttAchById(Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(Integer id);
}
