package com.hxj.book.view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hxj.book.entity.User;
import com.hxj.book.util.XmlUtil;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

public class Registry extends JFrame {

	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField nickField;
	private JLabel icon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registry frame = new Registry();
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
	public Registry() {
		setTitle("注册用户");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registry.class.getResource("/statics/注册.png"))); 
		setBounds(100, 100, 446, 270);
		setLocationRelativeTo(null); 
		getContentPane().setLayout(null);
		JLabel iconLabel = new JLabel("头像:");
		iconLabel.setIcon(new ImageIcon(Registry.class.getResource("/statics/头像.png")));
		iconLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		iconLabel.setBounds(28, 46, 60, 19);
		getContentPane().add(iconLabel);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801:");
		pwdLabel.setIcon(new ImageIcon(Registry.class.getResource("/statics/密 码.png")));
		pwdLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		pwdLabel.setBounds(28, 146, 60, 19);
		getContentPane().add(pwdLabel);
		
		JLabel confirmLabel = new JLabel("\u786E\u8BA4:");
		confirmLabel.setIcon(new ImageIcon(Registry.class.getResource("/statics/确认.png")));
		confirmLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		confirmLabel.setBounds(28, 189, 66, 17);
		getContentPane().add(confirmLabel);
		
		passwordField = new JPasswordField(); 
		passwordField.setToolTipText("密码");
		passwordField.setBounds(98, 147, 152, 22);
		getContentPane().add(passwordField);
		
		confirmPasswordField = new JPasswordField(); 
		confirmPasswordField.setToolTipText("确认密码");
		confirmPasswordField.setBounds(98, 189, 152, 22);
		getContentPane().add(confirmPasswordField);
		
		ImageIcon image =  new ImageIcon(Registry.class.getResource("/statics/default.jpg"));
		image.setImage(image.getImage().getScaledInstance(77,72,Image.SCALE_DEFAULT));
		icon = new JLabel(image);
		icon.setBounds(113, 24, 77, 72);
		getContentPane().add(icon);
//		System.out.println(icon.getIcon().toString().substring(6));
		
		JButton chooseIconLabel = new JButton("选择头像");
		chooseIconLabel.setIcon(new ImageIcon(Registry.class.getResource("/statics/图片.png")));
		chooseIconLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		chooseIconLabel.setToolTipText("选择头像"); 
		chooseIconLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String path = null;
				JFileChooser fileChooser = new JFileChooser();
				FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
				System.out.println(fsv.getHomeDirectory());                //得到桌面路径
				fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
				fileChooser.setDialogTitle("请选择要使用的头像");
				fileChooser.setApproveButtonText("确定");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG or jpg images", "png","jpg");
				fileChooser.setFileFilter(filter);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				result = fileChooser.showOpenDialog(null);
				if (JFileChooser.APPROVE_OPTION == result) {
				     path=fileChooser.getSelectedFile().getPath();
				     System.out.println("path: "+path);
				     ImageIcon image =  new ImageIcon(path);
				     image.setImage(image.getImage().getScaledInstance(77,72,Image.SCALE_DEFAULT));
				     icon.setIcon(image);
				     repaint();
				}
			}
		});
		chooseIconLabel.setBounds(216, 45, 126, 22);
		getContentPane().add(chooseIconLabel);
		
		JButton registBtn = new JButton("注册");
		registBtn.setIcon(new ImageIcon(Registry.class.getResource("/statics/注册.png")));
		registBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		registBtn.setToolTipText("确定注册");
		registBtn.addActionListener(e -> {
			regisetry();
		});
		registBtn.setBounds(273, 188, 97, 23);
		getContentPane().add(registBtn);
		
		JLabel nickLabel = new JLabel("\u6635\u79F0:");
		nickLabel.setIcon(new ImageIcon(Registry.class.getResource("/statics/昵称.png")));
		nickLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		nickLabel.setBounds(28, 106, 66, 17);
		getContentPane().add(nickLabel);
		
		nickField = new JTextField(); 
		nickField.setToolTipText("昵称");
		nickField.setColumns(10);
		nickField.setBounds(98, 106, 152, 22);
		getContentPane().add(nickField);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true); 
		setResizable(false); 
	}


	private void regisetry() {
		String username = nickField.getText().trim();
		String pwd = passwordField.getText().trim();
		String confirmPwd = confirmPasswordField.getText().trim();
		String msg = "";
		if(!pwd.equals(confirmPwd)) {
			msg = "两次输入密码不一致！";
		}
		if(username.equals("") || pwd.equals("") || confirmPwd.equals("")) {
			msg = "昵称或密码不能为空！";
		}
		Document document = XmlUtil.getDocument("user");
		List<Element> elements = document.getRootElement().elements();
		for(Element element : elements) {
			if(username.equals(element.elementText("username"))) {
				msg = "用户已存在";
				break;
			} 
		}
		if(!"".equals(msg)) {
			JOptionPane.showMessageDialog(null, msg,"提示", JOptionPane.WARNING_MESSAGE, 
					new ImageIcon(Registry.class.getResource("/statics/提醒.png")));
			return;
		}
		
		String id = elements.get(elements.size() - 1).attributeValue("ID");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(3);
        numberFormat.setGroupingUsed(false);
        id = numberFormat.format(Integer.parseInt(id) + 1);
        String str = icon.getIcon().toString();
        if(!str.equals(Registry.class.getResource("/statics/default.jpg").toString())) { 
            String parent = str.substring(0, str.lastIndexOf("\\"));
            String child = str.substring(str.lastIndexOf("\\")+1);
            System.out.println(parent);
            System.out.println(child);
            try {
            	File file = new File("icon", Math.random()+child);
//            	System.out.println(file.getAbsolutePath()); 
    			FileInputStream fis = new FileInputStream(new File(parent, child)); 
    			FileOutputStream fos = new FileOutputStream(file);
    			FileChannel in = fis.getChannel();
    			FileChannel out = fos.getChannel();
    			in.transferTo(0, in.size(), out);
//    			int len = -1;
//    			byte[] buf = new byte[1024*10];
//    			try {
//					while((len=fis.read(buf)) != -1) {
//						fos.write(buf, 0, len); 
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
    			str = file.getAbsolutePath();
    			fis.close();
    			fos.close(); 
    		} catch (IOException e) { 
    			e.printStackTrace();
    		}
        }else {
        	str = str.substring(6);
        }
        System.out.println(str);
        User user = new User(id, username, pwd, str);
        XmlUtil.getBeanToXml(document, user); 
        XmlUtil.writeXML(document, "user");
        
		JOptionPane.showMessageDialog(null, "注册成功,点击确定关闭窗口","提示", JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(Registry.class.getResource("/statics/成功.png")));
		this.dispose();
		
	}
}
