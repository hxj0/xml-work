package com.hxj.book.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hxj.book.entity.User;
import com.hxj.book.util.XmlUtil;
import com.hxj.book.view.component.BackGroundImagePanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		Document document = XmlUtil.getDocument("user");
		XmlUtil.writeXML(document, "user");
		document = XmlUtil.getDocument("book");
		XmlUtil.writeXML(document, "book");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/statics/图书知识库.png")));
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 209);
		setLocationRelativeTo(null);
		contentPane = new BackGroundImagePanel(Login.class.getResource("/statics/beijing.jpg"));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLabel = new JLabel("账号：");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		idLabel.setIcon(new ImageIcon(Login.class.getResource("/statics/账号.png")));
		idLabel.setBounds(28, 26, 72, 38);
		contentPane.add(idLabel);
		
		JLabel pwdLabel = new JLabel("密码：");
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		pwdLabel.setIcon(new ImageIcon(Login.class.getResource("/statics/密 码.png")));
		pwdLabel.setBounds(28, 69, 72, 38);
		contentPane.add(pwdLabel);
		
		idField = new JTextField();
		idField.setFont(new Font("宋体", Font.PLAIN, 16));
		idField.setBounds(105, 31, 177, 29);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JButton registBtn = new JButton("注册");
		registBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		registBtn.setIcon(new ImageIcon(Login.class.getResource("/statics/注册.png")));
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registry();
			}
		});
		registBtn.setBounds(51, 122, 97, 23);
		contentPane.add(registBtn);
		
		JButton loginBtn = new JButton("登录");
		loginBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		loginBtn.setIcon(new ImageIcon(Login.class.getResource("/statics/登录.png")));
		loginBtn.addActionListener(e->{ 
			login(); 
		});
		loginBtn.setBounds(172, 122, 97, 23);
		contentPane.add(loginBtn);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 16));
		passwordField.setBounds(105, 74, 177, 29);
		contentPane.add(passwordField);
		setVisible(true);
		setResizable(false);
	}

	protected void registry() {
		new Registry();
	} 

	private void login() { 
		String username = idField.getText();
		String pwd = passwordField.getText(); 
		if("".equals(username) || "".equals(pwd)) {
			JOptionPane.showMessageDialog(null, "用户名或密码为空！","提示", JOptionPane.WARNING_MESSAGE, 
					new ImageIcon(Registry.class.getResource("/statics/错误.png")));
			return;
		}
		List<Element> elements = XmlUtil.getDocument("user").getRootElement().elements();
		for(Element element : elements) {
			if(element.elementText("username").equals(username) && element.elementText("password").equals(pwd)) {
				new Main(new User(element.attributeValue("ID"), element.elementText("username"), element.elementText("password"), element.elementText("iconpath")));
				this.dispose();
				return; 
			}
		}
		JOptionPane.showMessageDialog(null, "用户名或密码错误！","提示", JOptionPane.WARNING_MESSAGE, 
				new ImageIcon(Registry.class.getResource("/statics/错误.png")));
	}
}
