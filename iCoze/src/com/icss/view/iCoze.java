package com.icss.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import com.icss.po.Friend;
import com.icss.po.User;

public class iCoze extends JFrame implements ActionListener {

	User user = null;
	Friend friend = null;
	List<Friend> friends = new ArrayList<Friend>();
	String[][] friendsList;

	// fromFriendMessage���ڴ���������������촰�ڵļ�ֵ�ԣ�����һ���������򿪶�����촰��
	public static Map<String, PersonChat> fromFriendMessage = new HashMap<String, PersonChat>();

	Socket socket;

	InputStream input;
	InputStreamReader isReader = null;
	BufferedReader reader = null;
	OutputStream socketOut;
	PrintWriter pw = null;

	private JPanel contentPane;

	// ϵͳ����
	private Image sysicon;// ����ͼ��
	private TrayIcon trayIcon;
	private SystemTray systemTray;// ϵͳ����
	private PopupMenu pop = new PopupMenu(); // ����һ���Ҽ�����ʽ�˵�
	private MenuItem show = new MenuItem("�����");
	private MenuItem exit = new MenuItem("�˳�");

	/**
	 * Create the frame.
	 */
	public iCoze(String userName, Socket t) {

		socket = t;
		// user.setUserNo(userName);
		try {
			input = socket.getInputStream();
			isReader = new InputStreamReader(input);
			reader = new BufferedReader(isReader);
			socketOut = socket.getOutputStream();
			pw = new PrintWriter(socketOut, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doData();
		doInit(user, friends);
		// UDP�߳�
		Thread thread = new RecvUDP();
		thread.start();
	}

	public void doData() {
		try {
			String userDataString = reader.readLine();
			String dataString = reader.readLine();

			if (dataString == null) {
				dataString = userDataString;
			} else {
				dataString = userDataString.substring(0, userDataString.length() - 1) + ":[����];" + dataString;
			}
			String regex = ";";
			String regex2 = ":";
			String[] ssStrings = dataString.split(regex);
			friendsList = new String[ssStrings.length][6];
			for (int i = 0; i < ssStrings.length; i++) {
				friend = new Friend();
				String[] ssStrings2 = ssStrings[i].split(regex2);
				friendsList[i][0] = ssStrings2[0];
				friendsList[i][1] = ssStrings2[1];
				friendsList[i][2] = ssStrings2[2];
				friendsList[i][3] = ssStrings2[3];
				friendsList[i][4] = ssStrings2[4];
				friendsList[i][5] = ssStrings2[5];
				friend.setFriendId(Integer.parseInt(ssStrings2[0]));
				friend.setFriendNo(ssStrings2[1]);
				friend.setFriendNickname(ssStrings2[2]);
				friend.setFriendSex(ssStrings2[3]);
				friend.setFriendImage(ssStrings2[4]);
				friend.setFriendStat(ssStrings2[5]);
				friends.add(friend);
			}
			user = new User();
			String[] userData = userDataString.split(regex2);
			user.setUserId(Integer.parseInt(userData[0]));
			user.setUserNo(userData[1]);
			user.setUserNickname(userData[2]);
			user.setUserSex(userData[3]);
			user.setUserImage(userData[4]);
			user.setUserStat("[����]");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doInit(final User user, List<Friend> friends) {
		setTitle("iCoze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(950, 100, 283, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		// contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);

		// -------------------------------------
		ImageIcon icon3 = new ImageIcon("src\\images\\iCoze_title.jpg");
		JLabel imgJLabel = new JLabel(icon3);

		this.getLayeredPane().add(imgJLabel, new Integer(Integer.MIN_VALUE));
		imgJLabel.setBounds(0, 0, icon3.getIconWidth(), icon3.getIconHeight());
		// -------------------------------------

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 290, 70);
		panel.setLayout(null);
		contentPane.add(panel);

		// ͷ��
		ImageIcon icon = new ImageIcon("src\\images\\touxiang\\" + user.getUserImage());
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(10, 15, 41, 41);
		panel.add(lblNewLabel);

		// ����״̬
		// ImageIcon icon2 = new ImageIcon("src\\03.png");
		// JLabel label = new JLabel(icon2);
		// label.setBounds(63, 17, 19, 19);
		// panel.add(label);

		int stat = 0;
		if (user.getUserStat().equals("����")) {
			stat = 1;
		}
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(63, 17, 58, 19);
		comboBox.addItem("����");
		comboBox.addItem("����");
		comboBox.addItem("����");
		comboBox.setSelectedIndex(stat);
		// ���ߡ�������¼�����
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(comboBox.getSelectedItem().toString());

			}
		});
		panel.add(comboBox);

		// �ǳ�
		JLabel label2 = new JLabel();
		label2.setBounds(125, 17, 100, 20);
		label2.setFont(getFont());
		label2.setText(user.getUserNickname());
		panel.add(label2);

		// ����ǩ��
		JTextField jTextField = new JTextField();
		jTextField.setBounds(63, 42, 195, 20);
		jTextField.setText("ԭ���������ȵģ������ǿ�ġ���");
		jTextField.setBorder(null);
		jTextField.setEditable(true);
		jTextField.setAutoscrolls(true);
		panel.add(jTextField);
		panel.add(imgJLabel);
		// ѡ�--�����
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 70, 280, 381);
		panel2.setBackground(new Color(241, 201, 149));
		panel2.setLayout(null);
		contentPane.add(panel2);

		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.setBounds(0, 0, 280, 381);
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // ����ѡ��Ĳ��ַ�ʽ������
		panel2.add(jTabbedPane);

		// �����б�

		JPanel panel4 = new JPanel();
		panel4.setBounds(0, 0, 280, 381);
		panel4.setBackground(new Color(241, 201, 149));
		int x = friends.size();
		panel4.setLayout(new GridLayout(1, x));
		jTabbedPane.addTab("����", null, panel4, "����鿴�����б�");

		Vector<String> vector = new Vector<String>();

		// ���������������Ϊ�գ���Ӱ�����ĸ�ֵ
		// String[] nameStrings = null;
		Icon[] icons = new Icon[x];
		// Ϊ�������Ը�ֵ
		int i = 0;
		Iterator<Friend> iterator = friends.iterator();
		while (iterator.hasNext()) {
			Friend friend = (Friend) iterator.next();
			vector.add(friend.getFriendStat() + " " + friend.getFriendNickname());
			icons[i] = new ImageIcon("src\\images\\touxiang\\" + friend.getFriendImage());
			i++;
		}
		final JList list = new JList(vector);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)// ˫���������촰��
				{
					// setExtendedState(Frame.NORMAL);
					if (socket != null) {
						pw.println("Chat");
						pw.println(friendsList[list.getSelectedIndex()][1]);// �ϴ��Է���No

						try {
							String friendIP = reader.readLine();
							PersonChat pChat = fromFriendMessage.get(friendsList[list.getSelectedIndex()][2]);
							if (pChat == null) {
								pChat = new PersonChat(user.getUserNickname(), socket.getLocalAddress().toString(), friendsList[list.getSelectedIndex()][2], friendIP);
								fromFriendMessage.put(friendsList[list.getSelectedIndex()][2], pChat);
							}
							// setVisible(true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		list.setCellRenderer(new ShowPaint(icons));
		// list.setBorder(jTextField.getBorder());
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(getSize());

		panel4.add(scrollPane);

		// Ⱥ�б�
		JPanel panel5 = new JPanel();
		jTabbedPane.addTab("Ⱥ�б�", null, panel5, "����鿴Ⱥ�б�");

		// �·���ѡ������
		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 448, 280, 55);
		contentPane.add(panel3);

		JButton button = new JButton("ϵͳ����");
		button.addActionListener(this);
		button.setBounds(0, 450, 90, 40);
		panel3.add(button);

		JButton button1 = new JButton("��ӷ���");
		button1.addActionListener(this);
		button1.setBounds(100, 450, 90, 40);
		panel3.add(button1);

		JButton button2 = new JButton("����");
		button2.addActionListener(this);
		button2.setBounds(210, 450, 70, 40);
		panel3.add(button2);
		// panel3.getBackground();
		panel3.setBackground(new Color(241, 201, 149));

		// ϵͳ����icon��ʼ��
		sysicon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("mini.png"));// ����ͼ����ʾ��ͼƬ

		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();// ���ϵͳ���̵�ʵ��
			trayIcon = new TrayIcon(sysicon, "iCoze", pop);
			// wasw100
			pop.add(show);
			pop.add(exit);

			try {
				systemTray.add(trayIcon); // �������̵�ͼ��
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					dispose();// ������С��ʱdispose�ô���
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1 && e.getButton() != MouseEvent.BUTTON3) {// ��������̴������֣������˫������e.getClickCount()
						setVisible(true);
						setExtendedState(JFrame.NORMAL);// ���ô� frame ��״̬��
					}
				}
			});

			show.addActionListener(new ActionListener() { // ���"�����"�˵��󽫴�����ʾ����

				public void actionPerformed(ActionEvent e) {
					// systemTray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
					setVisible(true); // ��ʾ����
					setExtendedState(JFrame.NORMAL);
				}
			});
			exit.addActionListener(new ActionListener() { // ������˳����˵����Ƴ�����

				public void actionPerformed(ActionEvent e) {
					// �������дִ���˳�ʱִ�еĲ���
					System.exit(0); // �˳�����
				}
			});
		}// ����ͼ�겿�ֽ���

		// ������swing����
		setIconImage(sysicon);// ���ĳ���ͼ��
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("ϵͳ����")) {

		}
		if (e.getActionCommand().equals("��ӷ���")) {

		}
		if (e.getActionCommand().equals("����")) {
			new Search(socket);
		}
	}
}
