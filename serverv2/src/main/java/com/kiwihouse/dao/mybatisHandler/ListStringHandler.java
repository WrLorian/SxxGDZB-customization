package com.kiwihouse.dao.mybatisHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.poi.util.StringUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xin
 * @date 2020/5/29
 */
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListStringHandler extends BaseTypeHandler<List> {

    private List parse(String sqlJson) {
        if (sqlJson != null) {
            String[] arr = sqlJson.split("_###_");
            List<String> ret = new LinkedList<>();
            for (String s : arr) {
                if (!s.isEmpty()) {
                    ret.add(s);
                }
            }
            return ret;
        }
        return null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        String s = StringUtil.join(parameter.toArray(), "_###_");
        ps.setString(i, s);
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlJson = rs.getString(columnName);
        return parse(sqlJson);
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlJson = rs.getString(columnIndex);
        return parse(sqlJson);
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlJson = cs.getString(columnIndex);
        return parse(sqlJson);
    }
}
