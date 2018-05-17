package tree;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import data.IAdressData;
import view.EmployeePanel;
import view.ProfessionPanel;

public class ProfessionNode extends AnyNode {
	
	private EmployeeNode firstSon;
	public ProfessionNode parent;
	public ProfessionNode left;
	public ProfessionNode right;

	public ProfessionNode(INode parent, IAdressData adressObject) {
		super(parent, adressObject);
	}

	@Override
	public void addSon(IAdressData data) {
		EmployeeNode newSon = new EmployeeNode(this, data);
		
		if(firstSon == null){
			firstSon = newSon;
			return;
		}
		
//		if(newSon.compareTo(firstSon) < 0){
//			newSon.setBrother(firstSon);
//			firstSon = newSon;
//			return;
//		}
		
		EmployeeNode current = firstSon;
		while(current.getBrother() != null) {
			current = current.getBrother();
		}
		
		newSon.setBrother(current.getBrother());
		current.setBrother(newSon);

	}

	@Override
	public Iterator<INode> iterator() {
		return new Iterator<INode>(){
			
			private EmployeeNode current = firstSon;
			
			private EmployeeNode result;
			
			private EmployeeNode predResult;
			
			@Override
			public AnyNode next(){
			
				predResult = result;
				
				result = current;
				current = current.getBrother();
				return result;
			}
			
			@Override 
			public void remove() {
				
				if( result == firstSon)
					firstSon = current;
				else
					predResult.setBrother(current);
			}

			@Override
			public boolean hasNext() {
				
				return current != null;
			}
		};
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		EmployeeNode current = firstSon;
		int i = 0;
		while(arg0 != i) {
			current = current.getBrother(); 
			i++;
		}
		return current;
	}

	@Override
	public int getChildCount() {
		if (firstSon == null)
			return 0;
		else {
			EmployeeNode current = firstSon;
			int i = 1;
			while(current.getBrother() != null) {
				current = current.getBrother();
				i++;
			}
			return i;
		}
	}

	public Component createPanel(){
		return new ProfessionPanel();
	}
	
	@Override
	public Component createSonPanel(){
		return new EmployeePanel();
	}
	
}
