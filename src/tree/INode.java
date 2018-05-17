package tree;

import java.awt.Component;
import java.io.Serializable;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import data.IAdressData;

public interface INode extends Serializable, Iterable<INode>, Comparable<INode>, TreeNode {
	public IAdressData getAdressObject();
	public void setAdressObject(IAdressData obj);
	public void addSon(IAdressData data);
	public void removeSon(INode removeSon);
	public void removeFromParent();
	public Iterator<INode> subTreeIterator();
	public Component createPanel();
	public Component createSonPanel();

	
}
