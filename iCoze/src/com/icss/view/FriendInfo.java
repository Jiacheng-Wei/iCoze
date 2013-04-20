package com.icss.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class FriendInfo extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Box baseBox,box1,box2,box3,box4,box5,box6,box7;
	
    JTextField txtNickName,txtNoName,txtSexName,txtRemarkName,txtQianName;
	
	public FriendInfo(){
		setBackground(Color.orange);
		this.Init();
	}
	@SuppressWarnings("static-access")
	public void Init(){
		//����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		Container me = getContentPane();
		me.setBackground(getBackground().pink);
		this.setTitle("ĳĳ������");              //Title
		this.setSize(new Dimension(450,260));  //���ô�С
		this.setLocationRelativeTo(this); //����
		setLayout(new FlowLayout());
		//������Ŀ
		JLabel labNickName = new JLabel("��         �ƣ�");
		this.txtNickName = new JTextField();
		txtNickName.setColumns(20);
		txtNickName.setText("liu");
		
		JLabel labSetNo = new JLabel("��        	 �룺");
		this.txtNoName = new JTextField();
		txtNoName.setColumns(20);
		txtNoName.setText("123456");
		txtNoName.setEditable(false);
		
		JLabel labSex = new JLabel("��         ��");
		String[] z = {"��","Ů","����"};
		JComboBox cboSex = new JComboBox(z);
		
		JLabel labRemark = new JLabel("��         ע��");
		this.txtRemarkName = new JTextField();
		txtRemarkName.setColumns(20);
		txtRemarkName.setText("��");
		
		JLabel labGeQian = new JLabel("����ǩ����");
		this.txtQianName = new JTextField();
		txtQianName.setColumns(20);
		txtQianName.setText("http");
 
		String[] y = {"1980","1981","1982","1983","1984","1985","1986",
				"1987","1988","1989","1990","1991","1992","1993","1994"};
		JComboBox cboBirthYear = new JComboBox(y);
		String[] m = {"01","02","03","04","05","06","07","08","09","10","11","12"} ;
		JComboBox cboBirthMonth = new JComboBox(m);
		String[] d = {"01","02","03","04","05","06","07","08","09","10","11","12",
				"13","14","15","16","17","18","19","20","21","22","23",
				"24","25","26","27","28","29","30","31"};
		JComboBox cboBirthDay = new JComboBox(d);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("a.jpg"));
		JLabel labBirth = new JLabel("��   �գ�",icon,JLabel.LEFT);
		//��ť	
		JButton reJButton = new JButton("�޸�");
		reJButton.addActionListener(this);
		JButton clJButton = new JButton("�ر�");
		clJButton.addActionListener(this);
		//�ϲ������м䲿��
		box1 = Box.createVerticalBox();
		box1.add(labNickName);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labSetNo);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labSex);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labBirth);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labGeQian);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labRemark);
		//�ϲ������ұ߲���
		box2 = Box.createVerticalBox();
		box2.add(txtNickName);
		box2.add(Box.createVerticalStrut(5));
		box2.add(txtNoName);
		box2.add(Box.createVerticalStrut(5));
		box2.add(cboSex);
		box2.add(Box.createVerticalStrut(5));
		
		box4 = Box.createHorizontalBox();
		box4.add(cboBirthYear);
		box4.add(Box.createHorizontalStrut(5));
		box4.add(cboBirthMonth);
		box4.add(Box.createHorizontalStrut(5));
		box4.add(cboBirthDay);
		box2.add(box4);
		box2.add(Box.createVerticalStrut(5));
		box2.add(txtQianName);
		box2.add(Box.createVerticalStrut(5));
		box2.add(txtRemarkName);
		
		box5 = Box.createHorizontalBox();
		box5.add(box1);
		box5.add(Box.createHorizontalStrut(10));
		box5.add(box2);
		//�ϲ����尴ť
		box3 = Box.createHorizontalBox();
		box3.add(reJButton);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(clJButton);
		
		baseBox = Box.createVerticalBox();
		baseBox.add(box5);
		baseBox.add(Box.createVerticalStrut(20));
		baseBox.add(box3);
		//���ͼƬ
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("b.jpg"));
		JLabel labImg = new JLabel("",icon1,JLabel.LEFT);
		//�ϲ�������߲���
		box7 = Box.createVerticalBox();
		box7.add(labImg);
		box7.add(Box.createVerticalStrut(100));
		//�ϲ�������
		box6 = Box.createHorizontalBox();
		box6.add(box7);
		box6.add(Box.createHorizontalStrut(10));
		box6.add(baseBox);
		add(box6);
		setVisible(true);
	}
	//��Ӽ�����
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "�޸�"){
			JOptionPane.showMessageDialog(this, "�޸ĳɹ���","��ʾ",JOptionPane.WARNING_MESSAGE);
		}
		if(e.getActionCommand()=="�ر�"){
			System.exit(0);  //�����Զ��ر�
		}
	}
	
	public static void main(String args[]){
	    new FriendInfo();
    }
}
