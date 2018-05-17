package view;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import data.EmployeeData;
import data.ProfessionData;
import data.IAdressData;
import tree.CitycomNode;
import tree.INode;
import tree.ModelForTree;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class MainGui {

	private JFrame frame;
	private JTree tree;
	private JTabbedPane tabbedPane;
	private ImagePanel imagePanel = new ImagePanel("/res/1.jpg");
	private CountDownLatch latch;
	private IAdressData obj;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}
	protected void showImagePanel() {
		tabbedPane.removeAll();
		buttonPanel.setVisible(false);
		tabbedPane.addTab("Adress Managment", imagePanel);
		tree.clearSelection();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tree.setModel(null);
				showImagePanel();
			}
		});
		frame.setBounds(100, 100, 642, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{266, 0, 0};
		gridBagLayout.rowHeights = new int[]{203, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				onSelection(e);
			}
		});
		scrollPane.setViewportView(tree);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tree, popupMenu);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 1;
		gbc_buttonPanel.gridy = 1;
		frame.getContentPane().add(buttonPanel, gbc_buttonPanel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(actionSave);
		buttonPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setAction(actionCancel);
		buttonPanel.add(btnCancel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mnFile.add(new JMenuItem(actionStore));
		mnFile.add(new JMenuItem(actionRestore));

		JMenu mnTree = new JMenu("Tree");
		menuBar.add(mnTree);
		mnTree.add(new JMenuItem(actionCreateRoot));
		mnTree.add(new JMenuItem(actionAdd));
		mnTree.add(new JMenuItem(actionRemove));
		mnTree.add(new JMenuItem(actionEdit));
		
		JMenu mnQuery = new JMenu("Query");
		menuBar.add(mnQuery);
		JMenuItem mntmApartmentWithThe = new JMenuItem(actionQuery1);
		mntmApartmentWithThe.setText("Apartment with the largest number of residents");
		mnQuery.add(mntmApartmentWithThe);
		JMenuItem menuItem_1 = new JMenuItem(actionQuery2);
		menuItem_1.setText("High-rise building count per street");
		mnQuery.add(menuItem_1);
		
		popupMenu.add(new JMenuItem(actionStore));
		popupMenu.add(new JMenuItem(actionRestore));
		popupMenu.add(new JSeparator());
		popupMenu.add(new JMenuItem(actionCreateRoot));
	}

	protected void onSelection(TreeSelectionEvent e) {
		INode node = (INode) tree.getLastSelectedPathComponent();
		if (node == null)
			return;
		
		IDataPanel panel = (IDataPanel) node.createPanel();
		panel.setData(node.getAdressObject());
		panel.setEditable(false);
		
		tabbedPane.removeAll();
		buttonPanel.setVisible(false);
		String text =  panel.getTabText();
		tabbedPane.addTab(text, (Component) panel);
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public JTree getTree() {
		return tree;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	

	
	private AbstractAction actionStore = new AbstractAction("Store tree") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onStore();
		}
	};
	protected void onStore() {
		if(tree.getModel() == null)
			return;
		JFileChooser fileChooser = new JFileChooser("Serialization");
		fileChooser.showSaveDialog(frame);
		
		try {
			File f = fileChooser.getSelectedFile();
			String fName = f.getAbsolutePath();
			FileOutputStream fileStream = new FileOutputStream(fName);
			ObjectOutputStream out = new ObjectOutputStream(fileStream);
			out.writeObject(tree.getModel());
			out.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(tree, "File opening error", "Tree Saving", JOptionPane.WARNING_MESSAGE);
			return;
		}
		tree.setModel(null);
	}
	
	private AbstractAction actionRestore = new AbstractAction("Restore tree") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onRestore();
		}
	};
	protected void onRestore() {
		FileDialog fileDialog = new FileDialog(frame);
		fileDialog.setMode(FileDialog.LOAD);
		fileDialog.setVisible(true);
		String dr = fileDialog.getDirectory();
		String fn = fileDialog.getFile();
		if (dr == null || fn == null)
			return;
		String fName = dr + fn;
		try {
			FileInputStream fis = new FileInputStream(fName);
			ObjectInputStream in = new ObjectInputStream(fis);
			TreeModel model = (TreeModel) in.readObject();
			
			tree.setModel(model);
			in.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(tree, "Error", "Deserealization", JOptionPane.WARNING_MESSAGE);
			return;
		}
		expandAll();
	}
	
	private AbstractAction actionCreateRoot = new AbstractAction("Create Root") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onCreateRoot();
		}
	};
	protected void onCreateRoot() {
		
		if(tree.getModel() != null) {
			String r = tree.getModel().getRoot().toString();
			JOptionPane.showMessageDialog(tree, r + " already exists", "Action CreateRoot", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		tabbedPane.removeAll();
		buttonPanel.setVisible(true);
		Component panel = new CitycomPanel();
		String text = ((IDataPanel) panel).getTabText();
		tabbedPane.addTab(text, panel);
		
		latch = new CountDownLatch(1);
		
		new Thread(() -> {
			try {
				latch.await();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			if(obj == null) {
				JOptionPane.showMessageDialog(tree, " null can`t be root", "Action CreateRoot", JOptionPane.WARNING_MESSAGE);
				return;
			}
			INode root = new CitycomNode(null, obj);
			TreeModel model = new ModelForTree(root);
			tree.setModel(model);
		}).start();
		
	}
	private AbstractAction actionAdd = new AbstractAction("Add") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onAdd();
		}
	};
		
	private INode getSelectedNode() {
		
		Object selectNode = tree.getLastSelectedPathComponent();
		if(selectNode == null) {
			JOptionPane.showMessageDialog(tree, " Node wasn`t selected", frame.getTitle(),JOptionPane.WARNING_MESSAGE);
			return null;
		}
		return (INode) selectNode;
	}
	
	private void expandAll() {
		for(int i = 0; i < tree.getRowCount(); i++)
			tree.expandRow(i);
	}
	
	protected void onAdd() {

		INode parent = getSelectedNode();
		if(parent == null) 
			return;
		
		Component panel = parent.createSonPanel();
		if(panel == null)
			return;
		
		tabbedPane.removeAll();
		buttonPanel.setVisible(true);
		String text = ((IDataPanel) panel).getTabText();
		tabbedPane.addTab(text, panel);
		
		latch = new CountDownLatch(1);
		
		new Thread(() -> {
			try {
				latch.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(obj == null) {
				JOptionPane.showMessageDialog(tree, " data can`t be null", "Action CreateRoot",JOptionPane.WARNING_MESSAGE);
				return;
			}
			parent.addSon(obj);
			

			tree.updateUI();
			expandAll();
		}).start();
	}

	private AbstractAction actionEdit = new AbstractAction("Edit") {

		@Override
		public void actionPerformed(ActionEvent e) {
			onEdit();
		}
	};
	protected void onEdit() {

		INode node = getSelectedNode();
		if(node == null)
			return;
		
		Component panel = node.createPanel();
		((IDataPanel)panel).setData(node.getAdressObject());
		
		tabbedPane.removeAll();
		buttonPanel.setVisible(true);
		String text = ((IDataPanel) panel).getTabText();
		tabbedPane.addTab(text, panel);
		
		latch = new CountDownLatch(1);
		
		new Thread(() -> {
			try {
				latch.await();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			if(obj == null) {
				JOptionPane.showMessageDialog(tree, " data can`t be null", "Action CreateRoot", JOptionPane.WARNING_MESSAGE);
				return;
			}
			INode parent = (INode) node.getParent();
			if (parent == null)
				node.setAdressObject(obj);
			else {
				parent.removeSon(node);
				parent.addSon(obj);
			}
			
			tree.updateUI();
		}).start();
		
	}
	
	private AbstractAction actionRemove = new AbstractAction("Remove") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onRemove();			
		}
	};
	protected void onRemove() {
		INode node = getSelectedNode();
		if(node == null)
			return;
		if(node.equals(tree.getModel().getRoot())){
			tree.setModel(null);
			return;
		}
		node.removeFromParent();
		
		tree.setSelectionPath(null);
		tree.updateUI();
	}
	
	
	private AbstractAction actionQuery1 = new AbstractAction("Query1") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onQ1();	
		}
	};
	
	private void selectNode(INode node) {
		int n = 0;
		INode root = (INode) tree.getModel().getRoot();
		Iterator<INode> itr = root.subTreeIterator();
		while(itr.hasNext()) {
			INode nod = itr.next();
			if(nod == node) {
				tree.setSelectionRow(n);
				return;
			}
			n++;
		}
	}
	
	protected void onQ1() {
		INode start = (INode) tree.getLastSelectedPathComponent();
		if(start == null)
			start = (INode) tree.getModel().getRoot();
		
		int maxResidents = 0;
		INode fullest = null;
		Iterator<INode> itr = start.subTreeIterator();
		while(itr.hasNext()) {
			INode node = itr.next();
			IAdressData data = node.getAdressObject();
			if(!(data instanceof EmployeeData))
				continue;
			int residents = ((EmployeeData) data).getSalary();
			if( residents > maxResidents) {
				maxResidents = residents;
				fullest = node;
			}
		}
		
		if(fullest != null) selectNode(fullest);
	}
	
	private AbstractAction actionQuery2 = new AbstractAction("Query2") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onQ2();	
		}
	};
	protected void onQ2() {
		INode bureau = (INode) tree.getModel().getRoot();
		StringBuilder sb = new StringBuilder();
		for(INode street : bureau) {
			int sum = 0;
			for(INode house : street) {
				ProfessionData data = (ProfessionData) house.getAdressObject();
				if(data.isSpec())
					sum++;
			}
			sb.append(street + ": " + sum + " high-storeys buildings\n");
		}
		
		tabbedPane.removeAll();
		buttonPanel.setVisible(false);
		QueryPanel queryPanel = new QueryPanel(this);
		tabbedPane.addTab("High-storeys buildings count per street", queryPanel);
		queryPanel.getTextArea().setText(sb.toString());
		
	}
	
	private AbstractAction actionSave = new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onSave();
		}
	};
	protected void onSave() {
		IDataPanel panel = (IDataPanel) tabbedPane.getComponentAt(0);
		obj = panel.getAdressData();
		latch.countDown();
		showImagePanel();
	}
	
	private AbstractAction actionCancel = new AbstractAction("Cancel") {
		@Override
		public void actionPerformed(ActionEvent e) {
			onCancel();	
		}
	};
	
	private JPanel buttonPanel;
	protected void onCancel() {
		obj = null;
		latch.countDown();
		showImagePanel();
	}

	
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
}
