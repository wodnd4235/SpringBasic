package com.kh.spring.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class StringArrayTypeHandler implements TypeHandler<String[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if(parameter!=null)
		{
			ps.setString(i,String.join(",",parameter));
		}
		else {
			ps.setString(i,"");
		}

	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		//java,c
		String columnValueStr=rs.getString(columnName);
		return columnValueStr.split(",");
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValueStr=rs.getString(columnIndex);
		return columnValueStr.split(",");
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValueStr=cs.getString(columnIndex);
		return columnValueStr.split(",");
	}

}
