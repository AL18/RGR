package tree;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import data.IAdressData;
import view.EmployeePanel;

public class EmployeeNode extends AnyNode {
	
	public EmployeeNode getBrother() {
		return brother;
	}

	public void setBrother(EmployeeNode brother) {
		this.brother = brother;
	}

	private EmployeeNode brother;

	public EmployeeNode(INode parent, IAdressData adressObject) {
		super(parent, adressObject);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSon(IAdressData data) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<INode> iterator() {

		return new Iterator<INode>(){
			@Override
		public boolean hasNext(){
			return false;
			}
			@Override
		public AnyNode next(){
		return null;
		}
		};
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	public Component createPanel(){
		return new EmployeePanel();
	}
	
	@Override
	public Component createSonPanel(){
		return null;
	}



}
