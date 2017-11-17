package com.logictech.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 下午1:14
 */
public class UserSqlProvider {
    public String selectUserVOSql(){
        return new SQL()
                .SELECT("u.*")
                .SELECT("c.name AS city")
                .SELECT("c.state AS province")
                .FROM("user_info u")
                .LEFT_OUTER_JOIN("city c ON c.id = u.city_id")
                .WHERE("u.id = #{id}")
                .toString();
    }
}
    