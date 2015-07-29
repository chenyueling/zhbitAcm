package com.xinhuo.util;

import java.util.List;

/**
 * 分页查询返回封装
 * @author xiezefan
 *
 * @param <T>
 */
public class QueryResult<T> {
	private List<T> resultList;
	private long totalrecord;
	
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
