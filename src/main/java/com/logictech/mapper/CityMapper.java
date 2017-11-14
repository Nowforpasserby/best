package com.logictech.mapper;

import com.logictech.entity.dto.CityDTO;
import com.logictech.entity.vo.UserVO;
import com.logictech.mapper.sql.UserSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:32
 */
public interface CityMapper extends Mapper<CityDTO> {
}
    