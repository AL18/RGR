package tree;

import java.awt.Component;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.TreeNode;

import data.IAdressData;

public abstract class AnyNode implements INode{
	private INode parent;
	private IAdressData adressObject;
	
	public void setParent(INode parent) {
		this.parent = parent;
	}

	public AnyNode(INode parent, IAdressData adressObject) {
		super();
		this.parent = parent;
		this.adressObject = adressObject;
	}
	
	public int compareTo(INode obj) {
		return getAdressObject().compareTo(obj.getAdressObject());
	}
	
	@Override
	public IAdressData getAdressObject() {
		return this.adressObject;
	}

	@Override
	public void setAdressObject(IAdressData obj) {
		this.adressObject = obj;
		
	}

	@Override
	public Iterator<INode> subTreeIterator() {
		return new SubTreeIterator(this);
	}

	@Override
	public Component createPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component createSonPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Enumeration<INode> children() {
		return new Enumeration<INode>() {
			Iterator<INode> itr = iterator();

			@Override
			public boolean hasMoreElements() {
				return itr.hasNext();
			}

			@Override
			public INode nextElement() {
				return itr.next();
			}
			
		};
	}
	
	public boolean getAllowsChildren() {
		return true;
	}

	public int getIndex(TreeNode node) {
		int i = 0;
		for (INode current : this)
			if (current == node)
				return i;
		return -1;
	}
	
	public TreeNode getParent() {
		return parent;
	}
	
	public boolean isLeaf() {
		return getChildCount() == 0;
	}
	
	public String toString() {
		return adressObject.toString();
	}
	
	@Override
	public void removeSon(INode removedSon) {
		Iterator<INode> itr = iterator();
		while (itr.hasNext()) {
			if(itr.next() == removedSon) {
				itr.remove();
				return;
			}
		}
	}

	@Override
	public void removeFromParent() {
		if(parent != null )
			this.parent.removeSon(this);
		
	}
	
	public void toList(List<INode> list) {
		list.add(this);
		for (INode node : this) {
			((AnyNode) node).toList(list);
		}
	}
	


}
class SubTreeIterator implements Iterator<INode>{
	List<INode> list = new Vector<>();
	int index = 0;
	
	public SubTreeIterator(INode node) {
		((AnyNode) node).toList(list);
	}
	
	@Override
	public boolean hasNext() {
		return index < list.size();
	}
	
	@Override
	public INode next() {
		return list.get(index++);
	}
	
	@Override
	public void remove() {
		INode delNode = list.get(--index);
		Iterator<INode> itr = new SubTreeIterator(delNode);
		while (itr.hasNext()) {
			list.remove(itr.next());
		}
		if(delNode.getParent() != null)
			delNode.removeFromParent();
		else
			delNode = null;
	}
}