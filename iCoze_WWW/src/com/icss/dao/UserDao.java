package com.icss.dao;

/**
 * �û��Լ��Ĳ��� 
 */

import com.icss.po.User;

public interface UserDao {

	// �����û�Id�����û�
	User findUserByUserId(int userId);

	// �����û�No�����û�
	User findUserByUserNo(String userNo);

	// ��֤�û�������
	boolean isValidateUser(String userNo, String userPassword);
	
	//�����û�IP
	int updateUserIP(String userNo, String userIP);
	
	//�û�ע��
	int addUserByReg(String userNickname, String userPassword, String userSex);

}
