package com.sepiroth.ooha.kinect;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stir.cs.homer.serviceDatabase.objects.Action;
import uk.ac.stir.cs.homer.serviceDatabase.objects.User;
import uk.ac.stir.cs.homer.serviceDatabase.objects.UserDevice;
import uk.ac.stir.cs.homer.serviceDatabase.queryBuilder.QueryObject;

import com.sepiroth.ooha.kinect.gesture.Gesture;
import com.sepiroth.ooha.kinect.gesture.GestureListModel;


public class KinectUI extends JFrame{
	
	private JTextField nameTF;
	private JTextField imagePathTF;
	private JTextField gNameTF;
	private JTextField gCtxtTF;
	private JTextField gPermTF;
	private GestureListModel gestureModel;
	private KinectSensorComponent kinect;
	
	public KinectUI(KinectSensorComponent kinect) {
		this.kinect = kinect;
		this.gestureModel = kinect.getGestureModel();
		init();
	}
	
	private void init(){
		
		//setup the main JFrame
		initMainFrame();
		
		// create the tabpane and add all required panels.
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(0, 0, 471, 348);
		
		// add the tabs to the main JFrame
		this.getContentPane().add(tabbedPane);
		
		// initialize the User management tab
		initUserPanel(tabbedPane);
		
		// initialize the Gesture management tab
		initGesturePanel(tabbedPane);
		
		// initialize the Gesture/Action binding tab
		initBindingPanel(tabbedPane);
		
		this.setVisible(true);
	}
	
	
	private void initBindingPanel(JTabbedPane tabbedPane) {
		JPanel bindingTab = new JPanel();
		tabbedPane.addTab("Gesture binding", null, bindingTab, null);
		bindingTab.setLayout(null);
		
		JPanel gestureListPanel = new JPanel();
		gestureListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gestureListPanel.setBounds(10, 11, 184, 301);
		bindingTab.add(gestureListPanel);
		gestureListPanel.setLayout(null);
		
		final JList<Gesture> gList = new JList<Gesture>();
		gList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gList.setVisibleRowCount(20);
		gList.setBounds(0, 0, 185, 301);
		gestureListPanel.add(gList);
		gList.setModel(gestureModel);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(204, 11, 252, 169);
		bindingTab.add(selectPanel);
		selectPanel.setLayout(null);
		
		final JComboBox<UserDevice> deviceComboB = new JComboBox<UserDevice>();
		deviceComboB.setEnabled(true);
		deviceComboB.setBounds(135, 11, 107, 22);
		selectPanel.add(deviceComboB);
		
		DefaultComboBoxModel<UserDevice> deviceModel = new DefaultComboBoxModel<UserDevice>();

		UserDevice[] devices = kinect.getDatabase().getAllUserDevices();
		
		for(UserDevice device : devices){
			deviceModel.addElement(device);
		}
		
		deviceComboB.setModel(deviceModel);
		
		JLabel lblDevice = new JLabel("Device");
		lblDevice.setBounds(11, 15, 46, 14);
		selectPanel.add(lblDevice);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(11, 48, 46, 14);
		selectPanel.add(lblAction);
		
		final JComboBox<Action> actionComboB = new JComboBox<Action>();
		actionComboB.setEnabled(true);
		actionComboB.setBounds(135, 44, 107, 22);
		selectPanel.add(actionComboB);
		
		final DefaultComboBoxModel<Action> actionModel = new DefaultComboBoxModel<Action>();
		
		actionComboB.setModel(actionModel);
		
		
		
		deviceComboB.addActionListener(new ActionListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<UserDevice> box = (JComboBox<UserDevice>)arg0.getSource();
				
				UserDevice device = box.getItemAt(box.getSelectedIndex());
				
				actionModel.removeAllElements();
				
				Action[] actions = kinect.getDatabase().getActions(new QueryObject().userDevice(device.getId()));
				
				for(Action action : actions){
					actionModel.addElement(action);
				}
				
			}});

		
		JButton btnBind = new JButton("Bind");
		btnBind.setBounds(151, 135, 91, 23);
		selectPanel.add(btnBind);
		
		btnBind.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Gesture g = gList.getSelectedValue();
				
				g.setAction(actionComboB.getItemAt(actionComboB.getSelectedIndex()));
				g.setDevice(deviceComboB.getItemAt(deviceComboB.getSelectedIndex()));
			}
			
		});
	}

	private void initGesturePanel(JTabbedPane tabbedPane) {

		JPanel gestureTab = new JPanel();
		tabbedPane.addTab("Gesture settings", null, gestureTab, null);
		
		JPanel gListPanel = new JPanel();
		gListPanel.setBounds(10, 11, 214, 285);
		gListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JList<Gesture> gestureList = new JList<Gesture>();
		
		gestureList.setVisibleRowCount(40);
		gestureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gestureList.setModel(gestureModel);
		
		
		
		JPanel gActionPanel = new JPanel();
		gActionPanel.setBounds(234, 11, 222, 285);
		gActionPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel gNamePanel = new JPanel();
		gNamePanel.setBounds(12, 7, 198, 20);
		
		JPanel gCtxtPanel = new JPanel();
		gCtxtPanel.setBounds(12, 33, 198, 20);
		gCtxtPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblContext = new JLabel("Context: ");
		gCtxtPanel.add(lblContext);
		
		gCtxtTF = new JTextField();
		gCtxtTF.setColumns(10);
		gCtxtPanel.add(gCtxtTF);
		
		JPanel gPermPanel = new JPanel();
		gPermPanel.setBounds(12, 59, 198, 20);
		gPermPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPermission = new JLabel("Permission: ");
		gPermPanel.add(lblPermission);
		
		gPermTF = new JTextField();
		gPermTF.setColumns(10);
		gPermPanel.add(gPermTF);
		
		JButton btnRecordGesture = new JButton("Record Gesture");
		btnRecordGesture.setBounds(44, 251, 144, 23);
		
	
		gNamePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName_1 = new JLabel("Name: ");
		gNamePanel.add(lblName_1);
		
		gNameTF = new JTextField();
		gNamePanel.add(gNameTF);
		gNameTF.setColumns(10);
		gActionPanel.setLayout(null);
		gActionPanel.add(gCtxtPanel);
		gActionPanel.add(gNamePanel);
		gActionPanel.add(gPermPanel);
		gActionPanel.add(btnRecordGesture);
		gestureTab.setLayout(null);
		gListPanel.setLayout(new BorderLayout(0, 0));
		gListPanel.add(gestureList);
		gestureTab.add(gListPanel);
		gestureTab.add(gActionPanel);

		final JTextField gName = gNameTF;
		final JTextField gCtxt = gCtxtTF;
		final JTextField gPerm = gPermTF;
		
		
		gestureList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				@SuppressWarnings("unchecked")
				JList<Gesture> list = (JList<Gesture>)arg0.getSource();
				
				Gesture g = list.getSelectedValue();
				
				gName.setText(g.getName());
				gCtxt.setText(g.getContext());
				gPerm.setText(g.getPermission());
			}
			
		});
		
		btnRecordGesture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = gName.getText();
				String context = gCtxt.getText();
				kinect.recordGesture(name,context);
			}
		});

		
	}

	private void initUserPanel(JTabbedPane tabbedPane) {
		JPanel userTab = new JPanel();
		tabbedPane.addTab("User settings", null, userTab, null);
		
		JPanel userListPanel = new JPanel();
		userListPanel.setBounds(10, 5, 114, 291);
		userListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(130, 149, 326, 147);
		infoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel userPicPanel = new JPanel();
		userPicPanel.setBounds(292, 6, 164, 137);
		infoPanel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel namePanel = new JPanel();
		infoPanel.add(namePanel);
		namePanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(10, 8, 85, 14);
		namePanel.add(lblName);
		
		nameTF = new JTextField();
		nameTF.setBounds(107, 5, 101, 20);
		nameTF.setHorizontalAlignment(SwingConstants.LEFT);
		nameTF.setAlignmentX(Component.LEFT_ALIGNMENT);
		namePanel.add(nameTF);
		nameTF.setColumns(20);
		nameTF.setMaximumSize(new Dimension(150,20));
		
		JPanel imagePanel = new JPanel();
		infoPanel.add(imagePanel);
		imagePanel.setLayout(null);
		
		JLabel imageLbl = new JLabel("ImageDB: ");
		imageLbl.setBounds(10, 9, 92, 14);
		imagePanel.add(imageLbl);
		
		imagePathTF = new JTextField();
		imagePathTF.setBounds(107, 6, 101, 20);
		imagePathTF.setMaximumSize(new Dimension(100, 20));
		imagePathTF.setHorizontalAlignment(SwingConstants.LEFT);
		imagePathTF.setColumns(20);
		imagePathTF.setAlignmentX(0.0f);
		imagePanel.add(imagePathTF);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Dialog", Font.BOLD, 10));
		btnBrowse.setBounds(220, 5, 87, 23);
		imagePanel.add(btnBrowse);
		userListPanel.setLayout(new BorderLayout(5, 5));
		
		JList<User> userList = new JList<User>();
	
		DefaultListModel<User> userModel = new DefaultListModel<User>();
		
		userModel.addElement(new User("TestUser".hashCode(),"TestUser"));
		
		userList.setModel(userModel);
		userTab.setLayout(null);
		userListPanel.add(userList, BorderLayout.CENTER);
		userTab.add(userListPanel);
		userTab.add(infoPanel);
		userTab.add(userPicPanel);
	}

	private void initMainFrame() {
		setAlwaysOnTop(true);
		setTitle("Kinect Configuration");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		this.setMinimumSize(new Dimension(477,371));
		getContentPane().setLayout(null);
	}

	public void updateGestureModel(){
		
		Gesture g = new Gesture(gNameTF.getText(),gCtxtTF.getText());
		gestureModel.updateModel(g);
	}
}