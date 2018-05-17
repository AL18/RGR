package tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import data.IAdressData;

public class ModelForTree extends DefaultTreeModel implements TreeModel  {

	private static final long serialVersionUID = 1L;

	public ModelForTree(INode root) {
		super(root);
	}

	public void add(INode parent, IAdressData data) {
		parent.addSon(data);
	}
	
	public void edit(INode node, IAdressData data) {
		if(node == root) 
			((INode) root).setAdressObject(data);
		else {
			INode parent = (INode) node.getParent();
			node.removeFromParent();
			parent.addSon(data);
		}
	}
	
	public void remove(INode node) {
		node.removeFromParent();
	}

}
