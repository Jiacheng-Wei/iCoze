package com.icss.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.CORBA.Request;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	private static final String IP = "192.168.40.23";
	private static final int PORT = 9001;
	Scanner in = new Scanner(System.in);
	Socket socket;
	// ---------------�������������------------
	InputStream input;
	InputStreamReader isreader = null;
	BufferedReader reader = null;
	OutputStream out;
	PrintWriter pw = null;
	String receiveMsg = "";
	String sendMsg = "";
	// ------------------------------------------
	// �����ؼ�
	JPanel panel;
	JLabel lblusername, lblpassword; // ��ǩ
	JTextField txtQQNum; // �����ı���
	JPasswordField txtPassword; // �����
	JButton btnLogin, btnExit, btnRegist; // ��ť

	// ���췽��
	public Login() {
		this.Init();
	}

	// ���ô���ķ���
	private void Init() {
		// -----------------------�Ż�����----------------------
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ----------------------------------------------------
		// -----------------��ӱ���---------------------------
		ImageIcon icon3 = new ImageIcon("src\\images\\login_background.jpg");
		JLabel imgJLabel = new JLabel(icon3);

		this.getLayeredPane().add(imgJLabel, new Integer(Integer.MIN_VALUE));
		imgJLabel.setBounds(0, 0, 400, 300);
		Container me = getContentPane();
		me.setLayout(null);
		((JComponent) me).setOpaque(false);
		// ----------------------------------------------------
		// ----------------- ���ô�������-----------------------
		this.setBounds(400, 200, 400, 300);
		this.setVisible(true);
		this.setTitle("��ӭ��½");
		this.setSize(new Dimension(400, 300));
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// -----------------------------------------------------
		// ------------------------����ͼƬ----------------------
		ImageIcon icon = new ImageIcon("src\\images\\login_touxiang.jpg");
		ImageIcon icon1 = new ImageIcon("src\\images\\login_small.gif");
		ImageIcon icon2 = new ImageIcon("src\\images\\QQBtn.png");
		// ------------------------------------------------------
		// -----------------------��������------------------------
		JPanel panel = new JPanel();
		panel = (JPanel) getContentPane();
		panel.setLayout(null);

		JLabel imageJLabel = new JLabel(icon);
		imageJLabel.setBounds(10, 110, 100, 100);

		JLabel labQQNum = new JLabel("��    �ţ�", icon1, JLabel.CENTER);
		labQQNum.setBounds(120, 110, 100, 30);
		this.txtQQNum = new JTextField(); // ----�˺�
		this.txtQQNum.setColumns(15);
		txtQQNum.setBounds(220, 110, 150, 30);

		JLabel labPassword = new JLabel("��    �룺", icon1, JLabel.CENTER);
		labPassword.setBounds(120, 150, 100, 30);
		this.txtPassword = new JPasswordField();
		txtPassword.setBounds(220, 150, 150, 30); // ----����
		this.txtPassword.setEchoChar('��');
		this.txtPassword.setColumns(15);

		JCheckBox passBox = new JCheckBox("��ס����");
		passBox.setBounds(140, 190, 80, 15);
		JCheckBox autoBox = new JCheckBox("�Զ���¼");
		autoBox.setBounds(230, 190, 80, 15);

		this.btnLogin = new JButton("��¼", icon2);
		this.btnLogin.addActionListener(this);
		btnLogin.setBounds(70, 220, 100, 40); // ----����
		this.btnRegist = new JButton("ע��", icon2);
		btnRegist.setBounds(220, 220, 100, 40);
		this.btnRegist.addActionListener(this);
		// --------------------------------------------------------
		panel.add(imageJLabel);
		panel.add(labQQNum);
		panel.add(txtQQNum);
		panel.add(labPassword);
		panel.add(txtPassword);
		panel.add(passBox);
		panel.add(autoBox);
		panel.add(btnLogin);
		panel.add(btnRegist);
		// TCP
		// ---------------------socket-----------------
		try {
			socket = new Socket(IP, PORT);

			input = socket.getInputStream();
			isreader = new InputStreamReader(input);
			reader = new BufferedReader(isreader);
			out = socket.getOutputStream();
			pw = new PrintWriter(out, true);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ---------------------------------------------
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ͨ��if�жϻ�ȡ����Ŀؼ�����
		if (e.getActionCommand() == "��¼") {

			// ����ȡ�˺ź�����
			String username = this.txtQQNum.getText();
			String password = this.txtPassword.getText();
			String regex = "[1-9][0-9]*";

			// --------------�ǿ��ж�-----------------
			if (username.length() < 1) {
				JOptionPane.showMessageDialog(this, "�˺Ų���Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				// ʹ�ؼ���ȡ����
				this.txtQQNum.requestFocus();
				return;
			}
			if (!username.matches(regex)) {
				// --------------�߼��ж�---------------
				JOptionPane.showMessageDialog(this, "�˺ű���Ϊ���ִ�������������", "��½��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (password.length() < 1) {
				JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				this.txtPassword.requestFocus();
				return;
			}

			// -------�ϴ��û���¼�˻���Ϣ------//
			pw.println("Login");

			pw.println(username);
			pw.flush();
			pw.println(password);
			pw.flush();

			try {
				receiveMsg = reader.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (receiveMsg.equals("0")) {
				// ��¼ʧ��
				// �����Ի���
				JOptionPane.showMessageDialog(this, "�Բ����˺Ż��������\n���ʵ����", "�Ի������", JOptionPane.WARNING_MESSAGE);
				// ���˺�ȫѡ
				this.txtQQNum.requestFocus(); // 1���ÿؼ���ȡ����
				this.txtQQNum.selectAll(); // 2�����ؼ�����ȫѡ
				this.txtPassword.setText(""); // 3���������ÿ�
				return;
			} else if (receiveMsg.equals("1")) {
				this.dispose();
				pw.println("iCoze");
				pw.println(username);
				new iCoze(username, socket);// �������
			}
		}
		if (e.getActionCommand() == "ע��") {
			
			new Register(socket);

		}
	}
}
