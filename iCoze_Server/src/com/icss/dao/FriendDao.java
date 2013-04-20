package com.icss.dao;

/**
 *���ں��ѵĲ���
 */

import java.util.List;
import com.icss.po.Friend;

public interface FriendDao {
	
	// ��Ӻ���
	int addFriendByFriendId(int userId, int friendId, String friendGroup);

	// ɾ������
	int deleteFriendByFriendId(int userId, int friendId);

	// �޸ĺ����ǳ�
	int modifyFriendByFriendId(int userId, int friendId, String friendGroup);

	// �鿴���к���
	List<Friend> findAllFriendsByUserId(int userId);

	// ���Һ���
	Friend findFriendByFriendNo(String friendNo);

}
