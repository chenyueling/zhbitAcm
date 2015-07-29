package com.xinhuo.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.xinhuo.util.QueryResult;


public interface Dao {
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * 删除实体
	 * @param entity	实体
	 */
	public void delete(Object entity);
	
	/**
	 * 批量删除实体
	 * @param <T>
	 * @param entityClass
	 * @param id
	 */
	public <T> void delete(Class<T> entityClass, String idstr);
	
	
	/**
	 * 查找实体
	 * @param <T>
	 * @param entityClass 实体类型
	 * @param entityId 实体ID
	 * @return	实体
	 */
	public <T> T findById(Class<T> entityClass, final Object id);
	
	/**
	 * 根据条件查询获取单个数据
	 * @param <T>
	 * @param entityClass
	 * @param whereSQL
	 * @return
	 */
	public <T> T find(Class<T> entityClass, String whereSQL, final Object[] whereParams);
	
	/**
	 * 根据条件查询获取多个数据
	 * @param <T>
	 * @param entityClass
	 * @param whereSQL
	 * @param whereParams
	 * @return
	 */
	public <T> List<T> findAll(Class<T> entityClass, String whereSQL, final Object[] whereParams);


	/**
	 * 根据条件查询且排序,如果whereSQL为空则只进行排序
	 * @param <T>
	 * @param entityClass
	 * @param whereSQL
	 * @param whereParams
	 * @param orderBy
	 * @return
	 */
	public <T> List<T> findAll(Class<T> entityClass, String whereSQL, final Object[] whereParams, final LinkedHashMap<String, String> orderBy);
	
	/**
	 * 获取分页数据
	 * @param <T>
	 * @param entityClass
	 * @param whereSQL
	 * @param whereParams
	 * @param searchTitle
	 * @param search
	 * @param page
	 * @param rows
	 * @param order
	 * @param sort
	 * @return
	 */
	public <T> QueryResult<T> getScrollDate(Class<T> entityClass, final String whereSQL, final Object[] whereParams, String searchTitle, final String search, final int page, final int rows, String sort, String order);
	
	/**
	 * 查询数据总数
	 * @param <T>
	 * @param entityClass
	 * @param whereSQL
	 * @param whereParams
	 * @return
	 */
	public <T> long getDataCount(Class<T> entityClass, final String whereSQL, final Object[] whereParams);
	
}
