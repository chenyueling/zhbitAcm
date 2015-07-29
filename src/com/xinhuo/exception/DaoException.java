package com.xinhuo.exception;
/**
 * 基础框架异常封装,包含工具类异常，Dao层异常
 * @author xiezefan
 *
 */
public class DaoException extends Exception {
	private static final long serialVersionUID = -7628170684424476047L;
	public DaoException(String resaon) {
		super(resaon);
	}
}
