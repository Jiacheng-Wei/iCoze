package com.icss.util;

import com.icss.dao.jdbcimpl.BaseDao;

public class Main {
	public static void main(String[] args) {
		
		// ��ʼ���û����ݿ�
		BaseDao baseDao = new BaseDao();
		baseDao.doInit();
		new TCPServer();
	}
}