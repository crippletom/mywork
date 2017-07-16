package com.xx.common.repository;

import java.io.Serializable;
import java.util.List;

import com.xx.common.vo.Page;

public interface BaseRepository {

	// JPA
	/**
	 * 新增
	 * @param entity
	 */
	public <E> void add(E entity);
	/**
	 * 修改
	 * @param entity
	 */
	public <E> void update(E entity);
	/**
	 * 删除
	 * @param clazz
	 * @param id
	 */
	public <E> void delete(Class<E> clazz,Serializable id);
	/**
	 * 查找
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <E> E get(Class<E> clazz, Serializable id);
	
	/**
	 * 查询列表
	 * @param ql
	 * @param clz
	 * @return
	 */
	public <E> List<E> queryFromJPA(String ql,Class<E> clz);
	
	
	// JDBC
	/**
	 * 使用SQL查询列表
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public <V> List<V> getFromNativeSQL(String sql, Class<V> clz, Object[] params);
	/**
	 * 分页查询
	 * @param page
	 * @param clz
	 * @param sql
	 * @param params
	 */
	public <V> void pagingFromNativeSQL(Page page, Class<V> clz, String sql, Object[] params);
	/**
	 * 执行本地SQL
	 * @param sql
	 * @param params
	 * @return
	 */
	public int execNativeSQL(String sql, Object[] params);
	
	/**
	 * 
	 * @param sql
	 * @param clz
	 * @param params
	 * @return
	 */
	public <V> V getValueFromNativeSQL(String sql, Class<V> clz, Object[] params);
}
