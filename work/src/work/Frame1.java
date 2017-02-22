package work;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Frame1 extends JFrame {
 
	private static final long serialVersionUID = 1L;
	int i =1;
	
	//基本按钮
	JToolBar toolBar= new JToolBar("工具栏");
	ImageIcon iconCut=new ImageIcon("cut.gif");
	ImageIcon iconCopy = new ImageIcon("copy.gif");
	ImageIcon iconPaste = new ImageIcon("paste.gif");
	JButton buttonCut= new JButton("剪切....",iconCut);
	JButton buttonCopy= new JButton("复制....",iconCopy);
	JButton buttonPaste= new JButton("粘贴....",iconPaste);
	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane= new JScrollPane(textArea);
	
	
	//工具栏
	
	JMenuBar menuBar= new JMenuBar();
	
	JMenu menuFile = new JMenu("文件(F)");
	JMenu menuEdit = new JMenu("编辑(E)");
	JMenu menuHelp = new JMenu("帮助(H)");
	
	JMenuItem menuItemFileNew = new JMenuItem("新建(N)");
	JMenuItem menuItemFileOpen = new JMenuItem("打开(O)");
	JMenuItem menuItemFileSaveAs = new JMenuItem("另存为(S)");
	JMenuItem menuItemFileExit = new JMenuItem("退出(X)");
	
	JCheckBoxMenuItem checkBoxMenuItemEditAutoWrap =
			new JCheckBoxMenuItem("自动换行");
	JMenuItem menuItemEditCut = new JMenuItem("剪切");
	JMenuItem menuItemEditCopy = new JMenuItem("复制");
	JMenuItem menuItemEditPaste = new JMenuItem("粘贴");
	
	JMenuItem menuItemHelpAbout = new JMenuItem("关于(A)");
	
	
	
	//弹出菜单
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem popupMenuItemCut = new JMenuItem("剪切");
	JMenuItem popupMenuItemCopy = new JMenuItem("复制");
	JMenuItem popupMenuItemPaste = new JMenuItem("粘贴");
	
	//文件选择器
    
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter fileFilter = 
    		new FileNameExtensionFilter("文本文件","txt");
    File file;
   
    //字体选项
    private String[] tx={"宋体","隶书"};
	private String[] tx1={"10","15","20","25","30","35","40","45","50"};
	private String[] tx2={"常规","粗体","斜体"};
	private JComboBox<String>cbb =new JComboBox<String>(tx);
	private JComboBox<String>tag =new JComboBox<String>(tx1);
	private JComboBox<String>tab =new JComboBox<String>(tx2);
	
	
	
	
	public Frame1(){
		this.setTitle("记事本");
		this.setBounds(100,200,450,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
		this.setVisible(true);
	}
	
	private void initialize(){
		//基本内容 
		buttonCut.setToolTipText("所选字符到剪贴版");
		buttonCopy.setToolTipText("复制所选字符到剪贴板");
		buttonPaste.setToolTipText("粘贴剪贴板的内容");
		toolBar.add(buttonCut);
		toolBar.add(buttonCopy);
		toolBar.add(buttonPaste);
		this.add(toolBar,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		
		
		//带工具栏
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuEdit.setMnemonic(KeyEvent.VK_E);
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuItemFileNew.setMnemonic(KeyEvent.VK_N);
		menuItemFileOpen.setMnemonic(KeyEvent.VK_O);
		menuItemFileSaveAs.setMnemonic(KeyEvent.VK_S);
		menuItemFileExit.setMnemonic(KeyEvent.VK_X);
		menuItemHelpAbout.setMnemonic(KeyEvent.VK_A);
		
		menuItemFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK,true));
		menuItemFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK,true));
		menuItemFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK,true));
	    
		menuFile.add(menuItemFileNew);               //添加文件菜单项
		menuFile.add(menuItemFileOpen);
		menuFile.add(menuItemFileSaveAs);
		menuFile.addSeparator();                     //添加菜单项分隔符
		menuFile.add(menuItemFileExit);
		
		menuEdit.add(checkBoxMenuItemEditAutoWrap);  //添加编辑菜单项
		menuEdit.addSeparator();
		menuEdit.add(menuItemEditCut);
		menuEdit.add(menuItemEditCopy);
		menuEdit.add(menuItemEditPaste);
		
		menuHelp.add(menuItemHelpAbout);             //添加帮助菜单项
		
		menuBar.add(menuFile);                       //菜单栏添加菜单
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
		toolBar.add(cbb);
		toolBar.add(tag);
		toolBar.add(tab);
		
		this.setJMenuBar(menuBar);                    //窗体设置菜单栏
		
		cbb.addItemListener(new ItemHandler());
		tag.addItemListener(new ItemHandler());
		tab.addItemListener(new ItemHandler());
		
		menuItemFileExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		
		//弹出式菜单
		popupMenu.add(popupMenuItemCut);
		popupMenu.add(popupMenuItemCopy);
		popupMenu.add(popupMenuItemPaste);
		
		//文本区域鼠标监听器
		
	    textArea.addMouseListener(new MouseAdapter(){
	    	public void mouseReleased(MouseEvent e){
	    		
	    		
	    		if(e.getButton()==MouseEvent.BUTTON3){//e.isPopupTrigger() ){
	    			popupMenu.show(textArea,e.getX(),e.getY());
	    			
	    		
	    		}
	    	}
	    });
		
		//工具栏按钮多年工作实践监听器
	    buttonCut.addActionListener(new ActionHandler());
	    buttonCopy.addActionListener(new ActionHandler());
	    buttonPaste.addActionListener(new ActionHandler());
	    
	    //菜单项添加动作事件监听器
	    menuItemFileNew.addActionListener(new ActionHandler());
	    menuItemFileOpen.addActionListener(new ActionHandler());
	    menuItemFileSaveAs.addActionListener(new ActionHandler());
		
	    checkBoxMenuItemEditAutoWrap.addActionListener(new ActionHandler());
	    menuItemEditCut.addActionListener(new ActionHandler());
	    menuItemEditCopy.addActionListener(new ActionHandler());
	    menuItemEditPaste.addActionListener(new ActionHandler());
	    
	    menuItemHelpAbout.addActionListener(new ActionHandler());
	    
	    //弹出式菜单动作事件监听器
	    popupMenuItemCut.addActionListener(new ActionHandler());
	    popupMenuItemCopy.addActionListener(new ActionHandler());
	    popupMenuItemPaste.addActionListener(new ActionHandler());
	    
	    //文件对话框设置过滤器
	    
	    fileChooser.setFileFilter(fileFilter);
	    
	}
	
	class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==buttonCut
				||e.getSource()==menuItemEditCut
				||e.getSource()==popupMenuItemCut){
					textArea.cut();
			}
				else if(e.getSource()==buttonCopy
						||e.getSource()==menuItemEditCopy
						||e.getSource()==popupMenuItemCopy){
					textArea.copy();
				}
				else if(e.getSource()==buttonPaste
						||e.getSource()==menuItemEditPaste
						||e.getSource()==popupMenuItemPaste){
					textArea.paste();
				}
				else if(e.getSource()==menuItemFileNew){
					newFile();
				}
				else if(e.getSource()==menuItemFileOpen){
					openFile();
				}
				else if(e.getSource()==menuItemFileSaveAs){
					saveAsFile();
				}
				else if(e.getSource()==checkBoxMenuItemEditAutoWrap){
				    if(checkBoxMenuItemEditAutoWrap.isSelected()){
					textArea.setLineWrap(true);
				    }else{
				    	textArea.setLineWrap(false);
				    }
				}
				else if(e.getSource()==menuItemHelpAbout){
					JOptionPane.showMessageDialog(null, "Design:19 黎家乐");
				}
		}
	}

	//新建文件
	void newFile(){
		if(!textArea.getText().equals("")){
			saveFile();
		}
		textArea.setText(null);
		file=null;
		
		this.setTitle("新建记事本"+String.valueOf(i));
		i++;
	}
	
	void openFile(){
		if(!textArea.getText().equals("")){
			saveFile();
		}
		int option = fileChooser.showOpenDialog(this);
		if(option==JFileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
			try{
				FileReader fr = new FileReader(file);
				textArea.read(fr, null);
				this.setTitle(file.getName()+"记事本");
				fr.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, "异常："+e.getMessage());
			}
		}
	}
	
	void saveFile(){
		if(file!=null &&file.exists()){
			try{
				FileWriter fw = new FileWriter(file);
				textArea.write(fw);
				fw.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, "异常："+e.getMessage());
			}
		}
		else{
			saveAsFile();
		}
	}
	
	void saveAsFile(){
		int option = fileChooser.showSaveDialog(this);
		if(option == JFileChooser.APPROVE_OPTION){
			file= fileChooser.getSelectedFile();
			try{
				FileWriter fw= new FileWriter(file);
				textArea.write(fw);
				this.setTitle(file.getName()+"记事本");
				fw.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, "异常："+e.getMessage());
			}
		}
	}
	
	//字体选择
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          new Frame1();
	}
	class ItemHandler implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			if(cbb.getSelectedIndex()==0){
				Font f1 = new Font(tx[0],tab.getSelectedIndex(),Integer.valueOf((String) tag.getSelectedItem()));
				textArea.setFont(f1);
			}
			else if(cbb.getSelectedIndex()==1){
				Font f1 = new Font(tx[1],tab.getSelectedIndex(),Integer.valueOf((String) tag.getSelectedItem()));
				textArea.setFont(f1);
			}
			
		}
	}


}
