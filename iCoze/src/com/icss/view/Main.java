package com.icss.view;

/**
 * ������
 */
import javax.swing.SwingUtilities;

public class Main {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ˼����1���������� 2������ʹ��(�������ؼ�)
		// ʹ����͸��
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Login frm = new Login();
				frm.setVisible(true);
			}
		});
	}
}
