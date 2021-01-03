package com.hxj.book.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hxj.book.entity.Book;
import com.hxj.book.entity.User;
import com.hxj.book.util.XmlUtil;

import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField findField;
	DefaultTableModel tableModel;
	private JComboBox comboBox;
	public static List<Book> books;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the frame.
	 * @param user 
	 */
	public Main(User user) { 
		books = new ArrayList<>();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/statics/图书知识库.png")));
		setTitle("图书管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 603); 
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 144, 738, 399);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column == 5;
			}
		};
//		table.setEnabled(false);
		table.setFont(new Font("宋体", Font.PLAIN, 18));
		Object[][] data = null;  
		tableModel = new DefaultTableModel(data,new String[] {"序号","书名","作者","出版日期","价格","简介"});
		table.setModel(tableModel); 
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 18)); 
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {{setHorizontalAlignment(JLabel.CENTER);}});
		table.setRowHeight(30);
		table.getTableHeader().setBackground(new Color(36,200,250));
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellHeaderRenderer() {
			{
				setHorizontalAlignment(JLabel.CENTER); 
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).disableResizedPosting();
		scrollPane.setViewportView(table);
		
		JButton addBtn = new JButton("添加");
		addBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/添加.png")));
		addBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		addBtn.setBounds(11, 103, 97, 23);
		contentPane.add(addBtn);
		addBtn.addActionListener(e->{
			int id = 0;
			for(Book book : books) {
				id = Math.max(id, book.getId());
			}
			new Edit(true, new Book(id+1, "", "", "", 0.0, "", ""), this);
		});
		
		
		JButton delBtn = new JButton("删除");
		delBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/删 除 .png")));
		delBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		delBtn.setBounds(152, 103, 97, 23);
		contentPane.add(delBtn);
		delBtn.addActionListener(e->{
			int[] rows = table.getSelectedRows();
			if(rows.length <= 0)return;
			int isDelete = JOptionPane.showConfirmDialog(null, "该操作无法撤销，是否确定删除？", "提示", 
					 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(Main.class.getResource("/statics/提醒.png")));
            if (isDelete == JOptionPane.YES_OPTION) {
            	Document document = XmlUtil.getDocument("book");
            	Element root = document.getRootElement();
    			for(int row : rows) {
    				String id = tableModel.getValueAt(row, 0).toString();
    				System.out.println(id); 
    				root.remove(root.elementByID(id));
    			}
    			XmlUtil.writeXML(document, "book");
    			initData();
            }
		});
		
		findField = new JTextField();
		findField.setFont(new Font("宋体", Font.PLAIN, 16));
		findField.setToolTipText("输入书名或作者");
		
		findField.setBounds(513, 104, 133, 23);
		contentPane.add(findField);
		findField.setColumns(10);
		
		JButton findBtn = new JButton("查找");
		findBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/查询.png")));
		findBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		findBtn.setBounds(651, 103, 97, 23);
		contentPane.add(findBtn);
		findBtn.addActionListener(e->{
			String text = findField.getText().trim();
			if("".equals(text))return;
			books.clear();
			tableModel.getDataVector().clear();
			List<Element> elements = XmlUtil.getDocument("book").getRootElement().elements(); 
			for(Element element : elements) {
				if(element.elementText("name").contains(text) || element.elementText("author").contains(text)) {
					Book book = new Book(Integer.parseInt(element.attributeValue("ID")), element.elementText("name"), element.elementText("author"), 
							element.elementText("publishDate"), Double.parseDouble(element.elementText("price")),
							element.elementText("description"), element.elementText("coverUrl"));
//					System.out.println(book);
					books.add(book);
					tableModel.addRow(new Object[] {element.attributeValue("ID"),element.elementText("name"),element.elementText("author"),
							element.elementText("publishDate"),element.elementText("price"),element.elementText("description")});
				}
			}
			repaint();
		});
		
		JLabel lblNewLabel = new JLabel("查询串:");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/statics/字符小.png")));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel.setBounds(434, 103, 80, 23);
		contentPane.add(lblNewLabel);
		
		JButton refreshBtn = new JButton("刷新");
		refreshBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/刷新.png")));
		refreshBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		refreshBtn.setBounds(651, 70, 97, 23);
		contentPane.add(refreshBtn);
		refreshBtn.addActionListener(e->{
			initData();
		});
		
		JLabel lblNewLabel_1 = new JLabel("当前用户：");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/statics/头像.png")));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(11, 37, 112, 33);
		contentPane.add(lblNewLabel_1);
		ImageIcon icon = new ImageIcon(user.getIconpath());
		icon.setImage(icon.getImage().getScaledInstance(80, 77, Image.SCALE_DEFAULT));
		JLabel iconLabel = new JLabel(icon); 
		iconLabel.setBounds(225, 6, 80, 77);
		contentPane.add(iconLabel);
		
		JLabel usernameLabel = new JLabel(user.getUsername());
		usernameLabel.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 18));
		usernameLabel.setBounds(122, 37, 97, 33);
		contentPane.add(usernameLabel);
		
		JLabel authority = new JLabel((user.getId().equals("001")? "管理员":"普通用户"));
		authority.setFont(new Font("宋体", Font.PLAIN, 16));
		authority.setBounds(315, 34, 112, 40);
		contentPane.add(authority);
		
		JButton editBtn = new JButton("编辑");
		editBtn.addActionListener(e->{
			if(table.getSelectedRow() < 0 || table.getSelectedRow() >= books.size())return;
			new Edit(true, books.get(table.getSelectedRow()), this); 
		});
		editBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/编辑.png")));
		editBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		editBtn.setBounds(285, 103, 97, 23);
		contentPane.add(editBtn);
		
		JButton detailBtn = new JButton("查看详情");
		detailBtn.setIcon(new ImageIcon(Main.class.getResource("/statics/详情查看.png")));
		detailBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		detailBtn.setBounds(513, 71, 133, 23);
		contentPane.add(detailBtn);
		
		JLabel lblNewLabel_1_1 = new JLabel("排序规则：");
		lblNewLabel_1_1.setIcon(new ImageIcon(Main.class.getResource("/statics/排序.png")));
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(513, 28, 112, 33);
		contentPane.add(lblNewLabel_1_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"序号", "价格", "出版日期"}));
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox.setBounds(635, 31, 97, 29);
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = comboBox.getSelectedIndex();
                    Collections.sort(books, new Comparator<Book>() {
                    	@Override
                    	public int compare(Book b1, Book b2) {
                    		if(index == 0) {
                        		return b1.getId().compareTo(b2.getId());
                    		}else if(index == 1) {
                    			return b1.getPrice().compareTo(b2.getPrice());
                    		}else {
                    			try { 
									Date d1 = sdf.parse(b1.getPublishDate());
									Date d2 = sdf.parse(b2.getPublishDate());
									return d2.compareTo(d1);
								} catch (ParseException e) {
									e.printStackTrace();
								}
                    		}
							return 0;
                    	}
					});
                    tableModel.getDataVector().clear();
                    for(Book book : books) {
                    	tableModel.addRow(new Object[] {book.getId(),book.getName(),book.getAuthor(),
            					book.getPublishDate(),book.getPrice(),book.getDescription()});
                    }
                    repaint();
                }
			}
		});
		
		detailBtn.addActionListener(e->{
			if(table.getSelectedRow() < 0 || table.getSelectedRow() >= books.size())return;
			new Edit(false, books.get(table.getSelectedRow()), null); 
		});
		setVisible(true);
		setResizable(false);
		initData();
		
		if(!user.getId().equals("001")) {
			addBtn.setVisible(false);
			delBtn.setVisible(false);
			editBtn.setVisible(false); 
		}
	}
	
	public void initData() {
		books.clear();
		tableModel.getDataVector().clear();
		List<Element> elements = XmlUtil.getDocument("book").getRootElement().elements(); 
		for(Element element : elements) {
			Book book = new Book(Integer.parseInt(element.attributeValue("ID")), element.elementText("name"), element.elementText("author"), 
					element.elementText("publishDate"), Double.parseDouble(element.elementText("price")),
					element.elementText("description"), element.elementText("coverUrl"));
//			System.out.println(book);
			books.add(book);
			tableModel.addRow(new Object[] {element.attributeValue("ID"),element.elementText("name"),element.elementText("author"),
					element.elementText("publishDate"),element.elementText("price"),element.elementText("description")});
		}
		comboBox.setSelectedIndex(0); 
		findField.requestFocus();
		repaint();
	}
}
