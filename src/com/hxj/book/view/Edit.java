package com.hxj.book.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hxj.book.entity.Book;
import com.hxj.book.util.XmlUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class Edit extends JFrame {

	private JPanel contentPane;
	private JTextField priceField;
	private JTextField dateField;
	private JTextField authorField;
	private JTextField nameField;

	/**
	 * Create the frame.
	 */
	public Edit(boolean flag, Book book, Main main) {
		setTitle(flag? "编辑" : "查看详情");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Edit.class.getResource("/statics/图书知识库.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("序号：");
		lblNewLabel.setIcon(new ImageIcon(Edit.class.getResource("/statics/数字化.png")));
		lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(75, 10, 68, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("书名：");
		lblNewLabel_1.setIcon(new ImageIcon(Edit.class.getResource("/statics/书、书本、书籍.png")));
		lblNewLabel_1.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(75, 51, 68, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("作者：");
		lblNewLabel_1_1.setIcon(new ImageIcon(Edit.class.getResource("/statics/用户管理.png")));
		lblNewLabel_1_1.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(75, 92, 68, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("日期：");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(Edit.class.getResource("/statics/日期.png")));
		lblNewLabel_1_1_1.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(75, 133, 68, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("价格：");
		lblNewLabel_1_1_1_1.setIcon(new ImageIcon(Edit.class.getResource("/statics/价格.png")));
		lblNewLabel_1_1_1_1.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(75, 174, 68, 31);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("介绍：");
		lblNewLabel_1_1_1_1_1.setIcon(new ImageIcon(Edit.class.getResource("/statics/介绍.png")));
		lblNewLabel_1_1_1_1_1.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(75, 231, 68, 31);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 272, 413, 84);
		contentPane.add(scrollPane);
		
		JTextArea desArea = new JTextArea();
		desArea.setText(book.getDescription()); 
		desArea.setLineWrap(true); 
		desArea.setFont(new Font("宋体", Font.PLAIN, 16));
		scrollPane.setViewportView(desArea);
		
		JLabel numberLabel = new JLabel(book.getId().toString());
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel.setFont(new Font("新宋体", Font.PLAIN, 16));
		numberLabel.setBounds(141, 11, 68, 31);
		contentPane.add(numberLabel);
		
		JLabel lblNewLabel_3 = new JLabel("封面:");
		lblNewLabel_3.setIcon(new ImageIcon(Edit.class.getResource("/statics/mtscover 智能封面.png")));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(274, 10, 68, 31);
		contentPane.add(lblNewLabel_3);

		ImageIcon imageIcon = new ImageIcon("".equals(book.getCoverUrl())?"src/statics/nocover.jpg":book.getCoverUrl());
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(168, 218, Image.SCALE_DEFAULT));
		JLabel coverLabel = new JLabel(imageIcon);
		coverLabel.setBounds(329, 44, 168, 218);
		contentPane.add(coverLabel);
		
		JButton saveBtn = new JButton("保存");
		saveBtn.setIcon(new ImageIcon(Edit.class.getResource("/statics/保存.png")));
		saveBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		saveBtn.setBounds(420, 375, 91, 31);
		contentPane.add(saveBtn);
		
		JButton backBtn = new JButton("返回");
		backBtn.setIcon(new ImageIcon(Edit.class.getResource("/statics/返回.png")));
		backBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		backBtn.setBounds(75, 375, 91, 31);
		contentPane.add(backBtn);
		backBtn.addActionListener(e->{
			dispose();
		});
		
		JButton chgBtn = new JButton("更换");
		chgBtn.setIcon(new ImageIcon(Edit.class.getResource("/statics/图片.png")));
		chgBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		chgBtn.setBounds(363, 10, 91, 31);
		contentPane.add(chgBtn);
		chgBtn.addActionListener(e->{
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
			     image.setImage(image.getImage().getScaledInstance(168, 218,Image.SCALE_DEFAULT));
			     coverLabel.setIcon(image);
			     repaint();
			}
		});
		
		priceField = new JTextField();
		priceField.setFont(new Font("宋体", Font.PLAIN, 16));
		priceField.setText(book.getPrice().toString());
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(141, 174, 91, 27);
		contentPane.add(priceField);
		priceField.setColumns(10);
		priceField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				System.out.println(ch);
				if(ch < KeyEvent.VK_0 || ch > KeyEvent.VK_9) {
					if(ch == '.' && !priceField.getText().contains("."))return;
					e.consume();
				}
			}
		});
		
		dateField = new JTextField();
		dateField.setText(book.getPublishDate());
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.setFont(new Font("宋体", Font.PLAIN, 16));
		dateField.setColumns(10);
		dateField.setBounds(141, 133, 124, 27);
		contentPane.add(dateField);
		
		authorField = new JTextField();
		authorField.setText(book.getAuthor());
		authorField.setHorizontalAlignment(SwingConstants.CENTER);
		authorField.setFont(new Font("宋体", Font.PLAIN, 16));
		authorField.setColumns(10);
		authorField.setBounds(141, 92, 124, 27);
		contentPane.add(authorField);
		
		nameField = new JTextField();
		nameField.setText(book.getName());
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("宋体", Font.PLAIN, 16));
		nameField.setColumns(10);
		nameField.setBounds(141, 51, 124, 27);
		contentPane.add(nameField);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		setVisible(true);
		setLocationRelativeTo(null); 
		
		numberLabel.requestFocus();
		
		if(!flag) {
			priceField.setEditable(false);
			dateField.setEditable(false);
			authorField.setEditable(false);
			nameField.setEditable(false);
			desArea.setEditable(false);
			saveBtn.setVisible(false); 
			chgBtn.setVisible(false); 
		}
	
		saveBtn.addActionListener(e->{
	        String msg = "";
			if(nameField.getText().trim().equals("")) {
				msg = "书名不能为空！";
				JOptionPane.showMessageDialog(null, msg,"提示", JOptionPane.PLAIN_MESSAGE, 
						new ImageIcon(Registry.class.getResource("/statics/错误.png")));
				return;
			}
			if(!Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", dateField.getText().trim())) {
				msg = "日期格式错误！";
				JOptionPane.showMessageDialog(null, msg,"提示", JOptionPane.PLAIN_MESSAGE, 
						new ImageIcon(Registry.class.getResource("/statics/错误.png")));
				return;
			}
			String coverUrl = "";
			if(!"src/statics/nocover.jpg".equals(coverLabel.getIcon().toString())) {
//				System.out.println(coverLabel.getIcon().toString());
				coverUrl = coverLabel.getIcon().toString();
				String parent = coverUrl.substring(0, coverUrl.lastIndexOf("\\"));
		        String child = coverUrl.substring(coverUrl.lastIndexOf("\\")+1);
				try {
	            	File file = new File("cover", Math.random()+child);
	    			FileInputStream fis = new FileInputStream(new File(parent, child)); 
	    			FileOutputStream fos = new FileOutputStream(file);
	    			int len = -1;
	    			byte[] buf = new byte[1024*10];
	    			try {
						while((len=fis.read(buf)) != -1) {
							fos.write(buf, 0, len); 
						}
					} catch (IOException e1) {
						e1.printStackTrace(); 
					}
	    			coverUrl = file.getAbsolutePath();
	    			fis.close();
	    			fos.close(); 
	    		} catch (IOException e1) { 
	    			e1.printStackTrace(); 
	    		}
			}
			Book book2 = new Book(Integer.parseInt(numberLabel.getText()), nameField.getText(), authorField.getText(),
					dateField.getText(), Double.parseDouble(priceField.getText()), desArea.getText(), coverUrl);
			System.out.println(book2); 

	        Document document = XmlUtil.getDocument("book"); 
	        Element element = document.getRootElement().elementByID(book2.getId()+"");
	        if(element != null) {
	        	List<Element> elements = element.elements();
	        	elements.get(0).setText(book2.getName());
	        	elements.get(1).setText(book2.getAuthor());
	        	elements.get(2).setText(book2.getPublishDate());
	        	elements.get(3).setText(book2.getPrice()+"");
	        	elements.get(4).setText(book2.getDescription());
	        	elements.get(5).setText(book2.getCoverUrl());
	        	msg = "更新成功，点击确定关闭窗口";
	        }else {
	        	XmlUtil.getBeanToXml(document , book2); 
	        	msg = "添加成功,点击确定关闭窗口";
	        }
	        
	        XmlUtil.writeXML(document, "book");
	        
			JOptionPane.showMessageDialog(null, msg,"提示", JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon(Registry.class.getResource("/statics/成功.png")));
			main.initData();
			this.dispose();
		});
	}
}
