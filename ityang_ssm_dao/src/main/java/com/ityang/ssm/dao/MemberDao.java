package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
    /**
     * 根据id 查询Member 会员
     * @param id
     * @return
     */
    @Select("select * from member where id=#{id}")
    Member findById(String id);
}
