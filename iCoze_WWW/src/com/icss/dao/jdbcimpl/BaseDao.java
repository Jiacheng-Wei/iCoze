package com.icss.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected int result;
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�������ݿ�����ʧ��");
		}
	}
	
	/**
	 * �������ݿ�����
	 */
	public void getConn() {
		String url = "jdbc:sqlserver://localhost:1433;dataBaseName=iCoze";
		String user = "sa";
		String password = "123456";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��,�������/���ݿ���/��½�˻������룡");
		}
	}

	/**
	 * �ر��������ݿ�����
	 */
	public void closeAll() {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�ر������쳣");
		}
	}

	/**
	 * ��ѯ���ݿ�
	 */
	public void doQuery(String sql, Object... params) {
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ѯ���ݿ��쳣��");
		}
	}

	/**
	 * �������ݿ⣺��ɾ��
	 */
	public void doOperate(String sql, Object... params) {
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �жϱ��Ƿ����
	 */
	boolean existTable(String tableName){
		DatabaseMetaData meta;
		try {
			meta = (DatabaseMetaData) conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, tableName, null);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void doInit() {
		// TODO Auto-generated method stub
		getConn();
		String sql = null;
		
		if (!existTable("Users")) {
			// ��ʼ����Users
			sql = "create table Users(userId int primary key identity(1,1), userNo nvarchar(10), userNickname nvarchar(10), userPassword nvarchar(20), userSex nvarchar(10), userImage nvarchar(100))";
			doOperate(sql);
			sql = "insert into Users values('10001', '��ѩ��', '123456', 'Ů', '13.jpg')";
			doOperate(sql);
			sql = "insert into Users values('10002', '����', '123456', '��', '15.jpg')";
			doOperate(sql);
			sql = "insert into Users values('10003', '����', '123456', '��', '12.jpg')";
			doOperate(sql);
			sql = "insert into Users values('10004', '����', '123456', '��', '16.jpg')";
			doOperate(sql);
			sql = "insert into Users values('10005', '����ΰ', '123456', '��', '13.jpg')";
			doOperate(sql);
			sql = "insert into Users values('10006', '�����', '123456', '��', '10.jpg')";
			doOperate(sql);
			System.out.println("====��ʼ����Users�ɹ�===");
		}else {
			System.out.println("Users���Ѵ���");
		}
		
		if (!existTable("UFRelations")) {
			// ��ʼ����UFRelations
			sql = "create table UFRelations(ufrId int primary key identity(1,1), userId int, friendId int, friendRemarks nvarchar(40), friendGroup nvarchar(40))";
			doOperate(sql);
			sql = "insert into UFRelations values(1, 2, 'С��', '����1')";
			doOperate(sql);
			sql = "insert into UFRelations values(1, 3, 'С��', '����1')";
			doOperate(sql);
			sql = "insert into UFRelations values(2, 1, 'С��', '����2')";
			doOperate(sql);
			sql = "insert into UFRelations values(2, 3, 'С��', '����2')";
			doOperate(sql);
			System.out.println("====��ʼ����UFRelations�ɹ�===");
		}else {
			System.out.println("UFRelations���Ѵ���");
		}
		
		if (!existTable("Groups")) {
			// ��ʼ����Groups
			sql = "create table Groups(groupId int primary key identity(1,1), groupNo nvarchar(10), groupName nvarchar(40), groupBroadcast nvarchar(40), groupImage nvarchar(40))";
			doOperate(sql);
			sql = "insert into Groups values('800000', 'iCoze', '����Ⱥ�㲥~~~~', '00.gif')";
			doOperate(sql);
			System.out.println("====��ʼ����Groups�ɹ�===");
		}else {
			System.out.println("Groups���Ѵ���");
		}
		
		if (!existTable("UGRelations")) {
			// ��ʼ����UGRelations
			sql = "create table UGRelations(ugrId int primary key identity(1,1), userId int, groupId int)";
			doOperate(sql);
			sql = "insert into UGRelations values(1, 1)";
			doOperate(sql);
			sql = "insert into UGRelations values(2, 1)";
			doOperate(sql);
			sql = "insert into UGRelations values(3, 1)";
			doOperate(sql);
			System.out.println("====��ʼ����UGRelations�ɹ�===");
		}else {
			System.out.println("UGRelations���Ѵ���");
		}
		
		if (!existTable("OutlineMsgs")) {
			// ��ʼ����OutlineMsgs
			sql = "create table OutlineMsgs(oId int primary key identity(1,1), userId int, friendId int, message nvarchar(1000))";
			doOperate(sql);
			sql = "insert into OutlineMsgs values(1, 2, '���10001�������ҵ�������Ϣ')";
			doOperate(sql);
			System.out.println("====��ʼ����OutlineMsgs�ɹ�===");
		}else {
			System.out.println("OutlineMsgs���Ѵ���");
		}
		
		if (!existTable("HistoryMsgs")) {
			// ��ʼ����HistoryMsgs
			sql = "create table HistoryMsgs(hId int primary key identity(1,1), userId int, friendId int, message nvarchar(1000))";
			doOperate(sql);
			sql = "insert into HistoryMsgs values(1, 2, '���10001�������ҵ���ʷ��Ϣ')";
			doOperate(sql);
			System.out.println("====��ʼ����HistoryMsgs�ɹ�===");
		}else {
			System.out.println("HistoryMsgs���Ѵ���");
		}
		
		if (!existTable("Logs")) {
			// ��ʼ����Logs
			sql = "create table Logs(lId int primary key identity(1,1), userId int, Action nvarchar(40), date date)";
			doOperate(sql);
			System.out.println("====��ʼ����Logs�ɹ�===");
		}else {
			System.out.println("Logs���Ѵ���");
		}
		
		closeAll();
	}
}
