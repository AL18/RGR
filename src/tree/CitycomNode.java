package tree;

import java.awt.Component;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import data.IAdressData;
import view.CitycomPanel;
import view.JEKPanel;

public class CitycomNode extends AnyNode {

	private JEKNode[] sonArray = new JEKNode[2];
	private int count = 0;	
	
	public CitycomNode(INode parent, IAdressData adressObject){
		super(parent, adressObject);
	}
	
	@Override
	public void addSon(IAdressData data) {
		if(sonArray.length == count)
			sonArray = Arrays.copyOf(sonArray, count + count /2);
		
		JEKNode node = new JEKNode(this, data);
		sonArray[count++] = node;
		
		//Arrays.sort(sonArray, 0, count-1);
	}
	
	@Override
	public Iterator<INode> iterator() {

		return new Iterator<INode>(){
			int current = 0;
			
			@Override
			public boolean hasNext(){
				return  current < count;
			}
			
			@Override
			public INode next(){
				return sonArray[current++];
			}
			
			@Override
			public void remove(){
				System.arraycopy(sonArray, current, sonArray, current - 1, count-- - current--);
			}
		};
	}
	
//	public CitycomNode(INode parent, IAdressData adressObject) {
//		super(parent, adressObject);
//	}
//
//	private Set<INode> streets = new TreeSet<>(); 
//	
//	@Override
//	public void addSon(IAdressData data) {
//		JEKNode node = new JEKNode(this, data);
//		streets.add(node);
//
//	}
//
//	@Override
//	public Iterator<INode> iterator() {
//		return streets.iterator();
//	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		
		return sonArray[arg0];
	}
	
	public Component createPanel(){
		return new CitycomPanel();
	}
	
	@Override
	public Component createSonPanel(){
		return new JEKPanel();
	}
	
	@Override
	public boolean getAllowsChildren() {
		return true;
	}

}
