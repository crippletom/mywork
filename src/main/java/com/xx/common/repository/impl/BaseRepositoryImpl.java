package com.xx.common.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xx.auth.entity.AuthUser;
import com.xx.common.repository.BaseRepository;
import com.xx.common.repository.mapper.BeanRowMapper;
import com.xx.common.vo.Page;

public abstract class BaseRepositoryImpl extends JdbcDaoSupport implements BaseRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource(name="dataSource")
	public final void initJdbcTemplate(DataSource dataSource){
		setDataSource(dataSource);
	}

	@Override
	public <E> void add(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public <E> void update(E entity) {
		entityManager.merge(entity);
	}

	@Override
	public <E> void delete(Class<E> clazz,Serializable id) {
		E e=entityManager.find(clazz, id);
		if(null!=e){
			entityManager.remove(e);
		}
	}

	@Override
	public <E> E get(Class<E> clazz, Serializable id) {
		return entityManager.find(clazz, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <E> List<E> queryFromJPA(String ql, Class<E> clz) {
		Query query=entityManager.createQuery(ql, clz);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V> List<V> getFromNativeSQL(String sql, Class<V> clz, Object[] params) {
		return getJdbcTemplate().query(sql, params, new BeanRowMapper(clz));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V> void pagingFromNativeSQL(Page page, Class<V> clz, String sql, Object[] params) {
		StringBuilder countSql=new StringBuilder();
		countSql.append("select count(*) from ( ").append(sql).append(" ) ");
		Long total=getValueFromNativeSQL(countSql.toString(),Long.class,params);
		page.setTotal(total);
		
		long start = page.getPageIndex() * page.getPageSize();
		StringBuilder querySql=new StringBuilder(sql.length()+100);
		if(start>0){
			querySql.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}else{
			querySql.append("select * from ( ");
		}
		querySql.append(sql);
		if(start>0){
			querySql.append(" ) row_ ) where rownum_ <= ").append(start + page.getPageSize())
			.append(" and rownum_ > ").append(start);
		}else{
			querySql.append(" ) where rownum <= ").append(page.getPageSize());
		}
		List<V> data= getJdbcTemplate().query(querySql.toString(),params,new BeanRowMapper(clz));
		page.setData(data);
	}

	@Override
	public int execNativeSQL(String sql, Object[] params) {
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public <V> V getValueFromNativeSQL(String sql, Class<V> clz, Object[] params) {
		return getJdbcTemplate().queryForObject(sql, params, clz);
	}

}
