package com.xx.common.repository.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class BeanRowMapper<R> implements RowMapper<R> {
	
	private Class<R> clz = null;
	
	protected static final int COLUMN_NOT_FOUND = -1;
	
	private int[] propertiesToColumns;
	
	public BeanRowMapper(Class<R> clz){
		this.clz=clz;
	}

	@Override
	public R mapRow(ResultSet rs, int rowNum) throws SQLException {
		PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(clz);
		ResultSetMetaData rsmd = rs.getMetaData();
		//将列下标与其对应的属性下标关联
		mapColumnsToProperties(rsmd,props);
		//生成实例对象
		R bean;
		try {
			bean = clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			bean = null;
			e.printStackTrace();
		}
		for(int i=0;i<propertiesToColumns.length;i++){
			if(propertiesToColumns[i]!=COLUMN_NOT_FOUND){
				PropertyDescriptor prop = props[i];
				Method setter = prop.getWriteMethod();
				Class<?> propType = setter.getParameterTypes()[0];
				if (propType.isPrimitive()) {
					propType = ClassUtils.primitiveToWrapper(propType);
				}
				Object value=getColumnValue(rs,propertiesToColumns[i],propType);
				Class clz=value.getClass();
				if ((value instanceof java.util.Date)) {
					String typeName = propType.getName();
					if ("java.sql.Date".equals(typeName)) {
						value = new java.sql.Date(((java.util.Date) value).getTime());
					} else if ("java.sql.Time".equals(typeName)) {
						value = new Time(((java.util.Date) value).getTime());
					} else if ("java.sql.Timestamp".equals(typeName)) {
						Timestamp tsValue = (Timestamp) value;
						int nanos = tsValue.getNanos();
						value = new Timestamp(tsValue.getTime());
						((Timestamp) value).setNanos(nanos);
					}
				}
				try {
					setter.invoke(bean, new Object[] { value });
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
	
	private void mapColumnsToProperties(ResultSetMetaData rsmd,PropertyDescriptor[] props) throws SQLException{
		propertiesToColumns=new int[props.length];
		Arrays.fill(propertiesToColumns, COLUMN_NOT_FOUND);
		for(int i=0;i<props.length;i++){
			String propName = props[i].getName();
			if ("class".equals(propName)) continue;
			Method setter = props[i].getWriteMethod();
			if(setter!=null){
				for(int k=1;k<=rsmd.getColumnCount();k++){
					String columnName=JdbcUtils.lookupColumnName(rsmd, k).replace("_", "");
					if(propName.equalsIgnoreCase(columnName)){
						propertiesToColumns[i]=k;
						break;
					}
				}
			}
		}
	}
	
	private Object getColumnValue(ResultSet rs,int index,Class<?> propType) throws SQLException {
		if(rs.getObject(index)==null) return null;
		if (propType.equals(String.class)) {
			return rs.getString(index);
		}
		if (propType.equals(Integer.class)) {
			return Integer.valueOf(rs.getInt(index));
		}
		if (propType.equals(Long.class)) {
			return Long.valueOf(rs.getLong(index));
		}
		if (propType.equals(Float.class)) {
			return Float.valueOf(rs.getFloat(index));
		}
		if (propType.equals(Double.class)) {
			return Double.valueOf(rs.getDouble(index));
		}
		if (propType.equals(BigDecimal.class)) {
			return rs.getBigDecimal(index);
		}
		if (propType.equals(Boolean.class)) {
			return Boolean.valueOf(rs.getBoolean(index));
		}
		if (propType.equals(Short.class)) {
			return Short.valueOf(rs.getShort(index));
		}
		if (propType.equals(Byte.class)) {
			return Byte.valueOf(rs.getByte(index));
		}
		if (propType.equals(Timestamp.class)) {
			return rs.getTimestamp(index);
		}
		if (propType.equals(Time.class)) {
			return rs.getTime(index);
		}
		if (propType.equals(SQLXML.class)) {
			return rs.getSQLXML(index);
		}
		return rs.getObject(index);
	}

}
