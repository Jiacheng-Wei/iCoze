package com.icss.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.icss.po.Friend;
import com.icss.po.User;
import com.icss.service.FriendService;
import com.icss.service.UserService;
import com.icss.service.impl.FriendServiceImpl;
import com.icss.service.impl.UserServiceImpl;

public class UDPServer extends Thread{

	// ����һ���˿ں�������·ͨ��
	private static final int PORT = 9528;
	// ����һ��DatagramSocekt����
	private DatagramSocket sendSocket,recvSocket;
	// ����һ��DatagramPacket����
	private DatagramPacket sendPacket,recvPacket;
	
	private String userNo, friendNo;

	public UDPServer(String userNo, String friendNo) {
		this.userNo = userNo;
		this.friendNo = friendNo;
	}
	public void run(){
		try {
			// ����1��ʵ����DatagramSocekt����
			recvSocket = new DatagramSocket(9527);
//			System.out.println("׼����ʼ�������ݡ���");
			while (true) {
				
				// ����2��ʵ����datagramPacket����
				byte[] buf = new byte[128]; // ����һ���ֽ����飬���ڽ����ֽ�������
				recvPacket = new DatagramPacket(buf, buf.length); // ��������
				
				// ����3��ʹ��DatagramSocekt�����receive()�����򿪷������˵Ľ��ܼ���
				recvSocket.receive(recvPacket);
				
				// ����4�������ݴ�ӡ��ʾ�ڿ���̨
				String str = new String(recvPacket.getData(),"utf-8");
							
				String message = str.trim();
				System.out.println(userNo+"˵"+message);
				
//				String regex = ":";
//				String[] strings = message.split(regex);
//				friendNo = strings[0];
//				
//				//���Һ��ѵ�IP��Ϣ
//				FriendService friendService = new FriendServiceImpl();
//				Friend friend = new Friend();
//				
//				friend = friendService.findFriendByFriendNo(friendNo);
//				friend.setFriendStat("����");
//				
//				if (friend.getFriendStat().equals("����")) {
//					String sysMessage = "#ϵͳ��ʾ���ú��Ѳ����ߣ�";
//					byte[] sendData = sysMessage.getBytes("utf-8");
//					datagramPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(datagramSocket.getInetAddress().toString()), 9528);
//					// ����3����������
//					datagramSocket.send(datagramPacket);
//					return;
//				}
				
				sendSocket = new DatagramSocket();
				message = (userNo + "> " + message);
				byte[] sendData = message.getBytes("utf-8");
				sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.40.22"), 9528);
				// ����3����������
				sendSocket.send(sendPacket);
				System.out.println("���ͳɹ�");
			}
		} catch (SocketException e) {
		} catch (IOException e) {
		}
	}
}
