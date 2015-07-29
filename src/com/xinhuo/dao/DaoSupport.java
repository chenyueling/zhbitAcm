package com.xinhuo.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinhuo.util.QueryResult;


@Transactional
public class DaoSupport implements Dao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void save(Object entity) {
		hibernateTemplate.save(entity);

	}
	public void update(Object entity) {
		hibernateTemplate.merge(entity);

	}
	public void delete(Object entity) {
		hibernateTemplate.delete(entity);	
	}
	
	public <T> void delete(Class<T> entityClass, String idstr) {
		String whereSQL = "";
		String[] id = idstr.split(";");
		boolean isFirst = true;
		for (int i=0; i<id.length; i++) {
			if (isFirst) {
				whereSQL = "id=?";
				isFirst = false;
			} else {
				whereSQL += " or id=?";
			}
		}
		List<T> list = this.findAll(entityClass, whereSQL, id);
		for (T t : list) {
			this.delete(t);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T findById(Class<T> entityClass, final Object id) {
		String entityName = this.getEntityName(entityClass);
		final String sql = "from " + entityName + " where id=?";
		List<T> entitys = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				query.setParameter(0, id);
				List list = query.list();
				return list;
			}
		});
		if (entitys != null && entitys.size() > 0) {
			return entitys.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, String whereSQL, final Object[] whereParams) {
		String entityName = this.getEntityName(entityClass);
		final String sql = "from " + entityName + " where " + whereSQL;
		List<T> entitys = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				for (int i=0; i<whereParams.length; i++) {
					query.setParameter(i, whereParams[i]);
				}
				List list = query.list();
				return list;
			}
		});
		if (entitys != null && entitys.size() > 0) {
			return entitys.get(0);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> List<T> findAll(Class<T> entityClass, String whereSQL, final Object[] whereParams) {
		String entityName = this.getEntityName(entityClass);
		final String sql = "from " + entityName + " where " + whereSQL;
		List<T> entitys = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				for (int i=0; i<whereParams.length; i++) {
					query.setParameter(i, whereParams[i]);
				}
				List list = query.list();
				return list;
			}
		});
		return entitys;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> List<T> findAll(Class<T> entityClass, final String whereSQL, final Object[] whereParams, final LinkedHashMap<String, String> orderBy) {
		String entityName = this.getEntityName(entityClass);
		final String sql = "from " + entityName + " " + (whereSQL == null ? "" : "where " + whereSQL) + buildOrderBy(orderBy);
		List<T> entitys = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				if (whereSQL != null) {
					for (int i=0; i<whereParams.length; i++) {
						query.setParameter(i, whereParams[i]);
					}
				}
				List list = query.list();
				return list;
			}
		});
		return entitys;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> List<T> findAll(Class<T> entityClass, final String whereSQL, final Object[] whereParams, final String sort, String order) {
		String entityName = this.getEntityName(entityClass);
		final String sql = "from " + entityName + " " + (whereSQL == null ? "" : "where " + whereSQL) + (sort == null ? "" : (" order by " + sort + " " + order));
		List<T> entitys = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				if (whereSQL != null) {
					for (int i=0; i<whereParams.length; i++) {
						query.setParameter(i, whereParams[i]);
					}
				}
				List list = query.list();
				return list;
			}
		});
		return entitys;
	}
	
	@SuppressWarnings("unchecked")
	public <T> QueryResult<T> getScrollDate(Class<T> entityClass, final String whereSQL, final Object[] whereParams, String searchTitle, final String search, final int page, final int rows, String sort, String order) {
		final String entityName = this.getEntityName(entityClass);
		QueryResult<T> qr = new QueryResult<T>();
		String subSQL = "";
		if ((whereSQL == null && search == null) == false) {
			subSQL = " where " + (search == null ? "" : searchTitle + " like ? ") + (whereSQL == null ? "" : (search == null ? whereSQL : "and " + whereSQL));
		}
		
		//查询分页数据 
		final String sql = "from " + entityName + subSQL + " " + "order by " + sort + " " + order;
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(sql);
				query.setFirstResult((page - 1) * rows);
				query.setMaxResults(rows);
				int index = 0;
				if (search != null) {
					query.setParameter(index++, "%" + search + "%");
				}
				if (whereSQL != null) {
					for (int i=0; i<whereParams.length; i++) {
						query.setParameter(index++, whereParams[i]);
					}
				}
				
				//System.out.println("HQL:" + query.getQueryString());
				return query.list();
			}
		});
		
		//查询数据总数
		final String countSQL = "select count(*) from " + entityName + subSQL;
		List countList = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(countSQL);
				int index = 0;
				if (search != null) {
					query.setParameter(index++, search);
				}
				if (whereSQL != null) {
					for (int i=0; i<whereParams.length; i++) {
						query.setParameter(index++, whereParams[i]);
					}
				}
				return query.list();
			}
		});
		
		if (countList != null) {
			qr.setTotalrecord(Long.parseLong(countList.get(0).toString()));
		} else {
			qr.setTotalrecord(0);
		}
		qr.setResultList(list);
		return qr;
	}
	
	@SuppressWarnings("unchecked")
	public <T> long getDataCount(Class<T> entityClass, final String whereSQL, final Object[] whereParams) {
		final String entityName = this.getEntityName(entityClass);
		final String countSQL = "select count(*) from " + entityName + (whereSQL == null ? "" : " where " + whereSQL);
		List countList = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(countSQL);
				int index = 0;
				if (whereSQL != null) {
					for (int i=0; i<whereParams.length; i++) {
						query.setParameter(index++, whereParams[i]);
					}
				}
				return query.list();
			}
		});
		
		if (countList != null) {
			return Long.parseLong(countList.get(0).toString());
		} else {
			return 0;
		}
	}
	
	
	


	/**
	 * 只查询特定参数
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	
	/**
	 * 根据实体获取实体类名
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	protected <T> String getEntityName(Class<T> entityClass) {
		String entityName = entityClass.getSimpleName();
		//从注解中获取实体类名
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity != null && !"".equals(entity.name())) {
			entityName = entity.name();
		}
		return entityName;
	}
	
	/**
	 * 根据orderBy生成sql片段
	 * @param orderBy
	 * @return
	 */
	protected String buildOrderBy(LinkedHashMap<String, String> orderBy) {
		StringBuffer orderBySQL = new StringBuffer("");
		if (orderBy != null && orderBy.size() > 0) {
			orderBySQL.append(" order by ");
			for (String key : orderBy.keySet()) {
				orderBySQL.append(key).append(" ").append(orderBy.get(key)).append(",");
			}
			orderBySQL.deleteCharAt(orderBySQL.length() - 1);
		}
		return orderBySQL.toString();
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
