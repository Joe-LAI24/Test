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
	
	//������ť
	JToolBar toolBar= new JToolBar("������");
	ImageIcon iconCut=new ImageIcon("cut.gif");
	ImageIcon iconCopy = new ImageIcon("copy.gif");
	ImageIcon iconPaste = new ImageIcon("paste.gif");
	JButton buttonCut= new JButton("����....",iconCut);
	JButton buttonCopy= new JButton("����....",iconCopy);
	JButton buttonPaste= new JButton("ճ��....",iconPaste);
	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane= new JScrollPane(textArea);
	
	
	//������
	
	JMenuBar menuBar= new JMenuBar();
	
	JMenu menuFile = new JMenu("�ļ�(F)");
	JMenu menuEdit = new JMenu("�༭(E)");
	JMenu menuHelp = new JMenu("����(H)");
	
	JMenuItem menuItemFileNew = new JMenuItem("�½�(N)");
	JMenuItem menuItemFileOpen = new JMenuItem("��(O)");
	JMenuItem menuItemFileSaveAs = new JMenuItem("���Ϊ(S)");
	JMenuItem menuItemFileExit = new JMenuItem("�˳�(X)");
	
	JCheckBoxMenuItem checkBoxMenuItemEditAutoWrap =
			new JCheckBoxMenuItem("�Զ�����");
	JMenuItem menuItemEditCut = new JMenuItem("����");
	JMenuItem menuItemEditCopy = new JMenuItem("����");
	JMenuItem menuItemEditPaste = new JMenuItem("ճ��");
	
	JMenuItem menuItemHelpAbout = new JMenuItem("����(A)");
	
	
	
	//�����˵�
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem popupMenuItemCut = new JMenuItem("����");
	JMenuItem popupMenuItemCopy = new JMenuItem("����");
	JMenuItem popupMenuItemPaste = new JMenuItem("ճ��");
	
	//�ļ�ѡ����
    
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter fileFilter = 
    		new FileNameExtensionFilter("�ı��ļ�","txt");
    File file;
   
    //����ѡ��
    private String[] tx={"����","����"};
	private String[] tx1={"10","15","20","25","30","35","40","45","50"};
	private String[] tx2={"����","����","б��"};
	private JComboBox<String>cbb =new JComboBox<String>(tx);
	private JComboBox<String>tag =new JComboBox<String>(tx1);
	private JComboBox<String>tab =new JComboBox<String>(tx2);
	
	
	
	
	public Frame1(){
		this.setTitle("���±�");
		this.setBounds(100,200,450,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
		this.setVisible(true);
	}
	
	private void initialize(){
		//�������� 
		buttonCut.setToolTipText("��ѡ�ַ���������");
		buttonCopy.setToolTipText("������ѡ�ַ���������");
		buttonPaste.setToolTipText("ճ�������������");
		toolBar.add(buttonCut);
		toolBar.add(buttonCopy);
		toolBar.add(buttonPaste);
		this.add(toolBar,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		
		
		//��������
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
	    
		menuFile.add(menuItemFileNew);               //����ļ��˵���
		menuFile.add(menuItemFileOpen);
		menuFile.add(menuItemFileSaveAs);
		menuFile.addSeparator();                     //��Ӳ˵���ָ���
		menuFile.add(menuItemFileExit);
		
		menuEdit.add(checkBoxMenuItemEditAutoWrap);  //��ӱ༭�˵���
		menuEdit.addSeparator();
		menuEdit.add(menuItemEditCut);
		menuEdit.add(menuItemEditCopy);
		menuEdit.add(menuItemEditPaste);
		
		menuHelp.add(menuItemHelpAbout);             //��Ӱ����˵���
		
		menuBar.add(menuFile);                       //�˵�����Ӳ˵�
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
		toolBar.add(cbb);
		toolBar.add(tag);
		toolBar.add(tab);
		
		this.setJMenuBar(menuBar);                    //�������ò˵���
		
		cbb.addItemListener(new ItemHandler());
		tag.addItemListener(new ItemHandler());
		tab.addItemListener(new ItemHandler());
		
		menuItemFileExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		
		//����ʽ�˵�
		popupMenu.add(popupMenuItemCut);
		popupMenu.add(popupMenuItemCopy);
		popupMenu.add(popupMenuItemPaste);
		
		//�ı�������������
		
	    textArea.addMouseListener(new MouseAdapter(){
	    	public void mouseReleased(MouseEvent e){
	    		
	    		
	    		if(e.getButton()==MouseEvent.BUTTON3){//e.isPopupTrigger() ){
	    			popupMenu.show(textArea,e.getX(),e.getY());
	    			
	    		
	    		}
	    	}
	    });
		
		//��������ť���깤��ʵ��������
	    buttonCut.addActionListener(new ActionHandler());
	    buttonCopy.addActionListener(new ActionHandler());
	    buttonPaste.addActionListener(new ActionHandler());
	    
	    //�˵�����Ӷ����¼�������
	    menuItemFileNew.addActionListener(new ActionHandler());
	    menuItemFileOpen.addActionListener(new ActionHandler());
	    menuItemFileSaveAs.addActionListener(new ActionHandler());
		
	    checkBoxMenuItemEditAutoWrap.addActionListener(new ActionHandler());
	    menuItemEditCut.addActionListener(new ActionHandler());
	    menuItemEditCopy.addActionListener(new ActionHandler());
	    menuItemEditPaste.addActionListener(new ActionHandler());
	    
	    menuItemHelpAbout.addActionListener(new ActionHandler());
	    
	    //����ʽ�˵������¼�������
	    popupMenuItemCut.addActionListener(new ActionHandler());
	    popupMenuItemCopy.addActionListener(new ActionHandler());
	    popupMenuItemPaste.addActionListener(new ActionHandler());
	    
	    //�ļ��Ի������ù�����
	    
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
					JOptionPane.showMessageDialog(null, "Design:19 �����");
				}
		}
	}

	//�½��ļ�
	void newFile(){
		if(!textArea.getText().equals("")){
			saveFile();
		}
		textArea.setText(null);
		file=null;
		
		this.setTitle("�½����±�"+String.valueOf(i));
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
				this.setTitle(file.getName()+"���±�");
				fr.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, "�쳣��"+e.getMessage());
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
				JOptionPane.showMessageDialog(this, "�쳣��"+e.getMessage());
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
				this.setTitle(file.getName()+"���±�");
				fw.close();
			}catch(IOException e){
				JOptionPane.showMessageDialog(this, "�쳣��"+e.getMessage());
			}
		}
	}
	
	//����ѡ��
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
