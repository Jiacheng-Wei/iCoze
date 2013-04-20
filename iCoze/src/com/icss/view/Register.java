package com.icss.view;
import java.awt.Color;
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

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class Register extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Socket socket;
	
	InputStream input;
	InputStreamReader isreader = null;
	BufferedReader reader = null;
	OutputStream out;
	PrintWriter pw = null;
	
	String receiveMsg = "";
	String sendMsg = "";

	Box baseBox,box1,box2,box3,box4,box5,box6;
	
    JTextField txtNickName;
	JPasswordField txtPasswordName,txtrPasswordName;
	JRadioButton rdoMale,rdoFemale,other;
	
	public Register(Socket s){
		socket = s;
		try {
			input = socket.getInputStream();
			isreader = new InputStreamReader(input);
			reader = new BufferedReader(isreader);
			out = socket.getOutputStream();
			pw = new PrintWriter(out, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(Color.orange);
		Init();
	}
	@SuppressWarnings("static-access")
	public void Init(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//-----------------------------���ñ���---------------------------------
		ImageIcon icon3 = new ImageIcon("src\\images\\reg_background.jpg");
    	JLabel imgJLabel = new JLabel(icon3);
    	this.getLayeredPane().add(imgJLabel,new Integer(Integer.MIN_VALUE));
    	imgJLabel.setBounds(0,0, 370, 270);
    	Container me = getContentPane();
    	me.setLayout(null);
    	((JComponent) me).setOpaque(false);
	   //-----------------------------------------------------------------------
		ImageIcon icon = new ImageIcon("src\\images\\QQBtn.png");
		this.setTitle("ע���˻�");              //Title
		this.setSize(new Dimension(370,265));  //���ô�С
		this.setLocationRelativeTo(this); //����
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		JLabel labNickName = new JLabel("��        �ƣ�");
		this.txtNickName = new JTextField();
		txtNickName.setColumns(20);
		
		JLabel labSetPassword = new JLabel("�������룺");
		this.txtPasswordName = new JPasswordField();
		txtPasswordName.setEchoChar('��');
		txtPasswordName.setColumns(20);
		
		JLabel labrepeatPassword = new JLabel("�ظ����룺");
		this.txtrPasswordName = new JPasswordField();
		txtrPasswordName.setEchoChar('��');
		txtrPasswordName.setColumns(20);
		

		ButtonGroup bg = new ButtonGroup();       //�Ա�ѡ��
		
		JLabel labSex = new JLabel("��       ��");
		rdoMale = new JRadioButton("��");
		rdoMale.setSelected(true);		
		rdoFemale = new JRadioButton("Ů");
		other = new JRadioButton("����");
		bg.add(rdoMale);
		bg.add(rdoFemale);
		bg.add(other);
 
		String[] y = {"1980","1981","1982","1983","1984","1985","1986",
				"1987","1988","1989","1990","1991","1992","1993","1994"};
		JComboBox cboBirthYear = new JComboBox(y);
		String[] m = {"01","02","03","04","05","06","07","08","09","10","11","12"} ;
		JComboBox cboBirthMonth = new JComboBox(m);
		String[] d = {"01","02","03","04","05","06","07","08","09","10","11","12",
				"13","14","15","16","17","18","19","20","21","22","23",
				"24","25","26","27","28","29","30","31"};
		JComboBox cboBirthDay = new JComboBox(d);
		JLabel labBirth = new JLabel("��       �գ�");
		 	
		JButton reJButton = new JButton("����ע��",icon);
		reJButton.addActionListener(this);
		
		box1 = Box.createVerticalBox();
		box1.add(labNickName);
		box1.add(Box.createVerticalStrut(15));
		box1.add(labSetPassword);
		box1.add(Box.createVerticalStrut(15));
		box1.add(labrepeatPassword);
		box1.add(Box.createVerticalStrut(15));
		box1.add(labSex);
		box1.add(Box.createVerticalStrut(15));
		box1.add(labBirth);
		
		box2 = Box.createVerticalBox();
		box2.add(txtNickName);
		box2.add(Box.createVerticalStrut(10));
		box2.add(txtPasswordName);
		box2.add(Box.createVerticalStrut(10));
		box2.add(txtrPasswordName);
		box2.add(Box.createVerticalStrut(10));
		
		box3 = Box.createHorizontalBox();
		box3.add(rdoMale);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(rdoFemale);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(other);
		
		box2.add(box3);
		box2.add(Box.createVerticalStrut(10));
		
		box4 = Box.createHorizontalBox();
		box4.add(cboBirthYear);
		box4.add(Box.createHorizontalStrut(2));
		box4.add(cboBirthMonth);
		box4.add(Box.createHorizontalStrut(2));
		box4.add(cboBirthDay);
		box2.add(box4);
		
		box5 = Box.createHorizontalBox();
		box5.add(box1);
		box5.add(Box.createHorizontalStrut(10));
		box5.add(box2);
		
		
		box6 = Box.createVerticalBox();
		box6.add(reJButton);
		
		baseBox = Box.createVerticalBox();
		baseBox.add(box5);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box6);
		add(baseBox);
		
		setVisible(true);
//		setResizable(false);     //���ɸı��С
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		pw.println("Register");
		if(e.getActionCommand() == "����ע��"){
			String username = this.txtNickName.getText();
			@SuppressWarnings("deprecation")
			String password = this.txtPasswordName.getText();
			@SuppressWarnings("deprecation")
			String rpassword = this.txtrPasswordName.getText();
			String userSex = null;
			if (rdoMale.isSelected()) {
				userSex = "��";
			}else if (rdoFemale.isSelected()) {
				userSex = "Ů";
			}else {
				userSex = "����";
			}
			
			//--------------�ǿ��ж�-----------------
			if(username.length()<1){
				JOptionPane.showMessageDialog(this, "�ǳƲ���Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);	
				//ʹ�ؼ���ȡ����
				this.txtNickName.requestFocus();
				return;
			}
			if(password.length()<1){
				JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);	
				this.txtPasswordName.requestFocus();
				return;
			}
			if(!rpassword.equals(password)){
				JOptionPane.showMessageDialog(this, "�ظ��������","��ʾ",JOptionPane.WARNING_MESSAGE);	
				this.txtPasswordName.setText(null);
				this.txtrPasswordName.setText(null);
				this.txtPasswordName.requestFocus();
				return;
			}
			
			pw.println(username);
			pw.println(password);
			pw.println(userSex);
			
			try {
				String userNo = reader.readLine();
				JOptionPane.showMessageDialog(this, "����QQ�����ǣ�"+userNo,"��ӭ����ΪQQһԱ", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
