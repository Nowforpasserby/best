package com.logictech.mapper;

import com.logictech.config.CommonMapperConfig;
import com.logictech.entity.dto.UserDTO;
import com.logictech.entity.vo.UserVO;
import com.logictech.mapper.sql.UserSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:32
 */
public interface UserMapper extends CommonMapperConfig<UserDTO> {
    /**
     * 页面展示的数据抽出
     * @param id
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "selectUserVOSql")
    UserVO selectUserVO(Integer id);
}
    