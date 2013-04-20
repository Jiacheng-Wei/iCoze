package com.icss.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ShowPaint extends JLabel implements ListCellRenderer {

	Icon[] icons;

	public ShowPaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowPaint(Icon[] icons) {
		super();
		this.icons = icons;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		String s = value.toString();
		setText(s);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));// ������Ϊ5�Ŀհױ߿�
		if (isSelected) {
			// setBackground(list.getSelectionBackground());
			// setForeground(list.getSelectionForeground());
			setBackground(new Color(252, 233, 161));
			setForeground(Color.BLUE);
		} else {
			// setBackground(list.getBackground());
			// setForeground(list.getForeground());
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
			// new Color(241, 201, 149)
		}
		setIcon(icons[index]);// ����ͼƬ
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		return this;
	}

}
