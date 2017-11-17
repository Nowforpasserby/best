package com.logictech.config;

import com.logictech.mapper.sql.CommonSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.Mapper;

/**
 *  定义自己需要的共同 Mapper 接口
 *  实现则在 com.logictech.mapper.sql.CommonSqlProvider
 *  参考文档 https://mapperhelper.github.io/docs/6.mymapper/
 * @author JG.Hannibal
 * @since 2017/11/17 下午5:37
 */
public interface CommonMapperConfig<T> extends Mapper<T> {
    /**
     * 一个简单的插入接口
     * @param t
     * @return
     */
    @InsertProvider(type = CommonSqlProvider.class, method = "dynamicSQL")
    int mInsert(T t);
}
    