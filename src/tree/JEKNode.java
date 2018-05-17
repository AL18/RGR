package tree;

import java.awt.Component;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import data.IAdressData;
import view.ProfessionPanel;
import view.JEKPanel;

public class JEKNode extends AnyNode {

	private JEKNode brother;
	
	private ProfessionNode rootSon;
	
	private ProfessionNode[] array = new ProfessionNode[3];
	private int count; 
	
	public JEKNode(INode parent, IAdressData userObject) {
		super(parent, userObject);
	}

	public JEKNode getBrother() {
		return brother;
	}

	public void setBrother(JEKNode brother) {
		this.brother = brother;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	@Override
	public Iterator<INode> iterator() {
		return new Iterator<INode>() {

			int current = 0;
			int index = 0; 
			
			{
				count=0;
				sizeTree(rootSon); 
				treeToArr(rootSon, index, array);
			}
			
			void sizeTree(ProfessionNode rootSon) {
				if(rootSon == null) 
					return; 
				sizeTree(rootSon.left);				
				sizeTree(rootSon.right);
				count++;
			}
			
			void treeToArr(ProfessionNode rootSon, int index, ProfessionNode[] arr) {
				if(rootSon == null) 
					return;
				treeToArr(rootSon.left, index, arr);
				treeToArr(rootSon.right, index, arr);	
				arr[index] = rootSon;
				index++;
			}
			
			@Override
			public boolean hasNext() {
				return current < count;
			}
			
			@Override
			public AnyNode next() {
				return array[current++];
			}
			
			@Override
			public void remove() {
				
				ProfessionNode node = array[current-1];
				System.out.println(node);
				System.out.println();
				ProfessionNode rightNode = node.right;
				ProfessionNode leftNode = node.left;
				
				if(rightNode != null) {
					rightNode.parent = null;
					//addRecursive(rightNode, null, rootSon);
					//addSon(rightNode.getAdressObject());
					System.out.println(node.right);
					System.out.println();
				}
				if(leftNode != null) {
					leftNode.parent = null;
					//addRecursive(leftNode, null, rootSon);
					//addSon(leftNode.getAdressObject());
					System.out.println(node.left);
					System.out.println();
				}
				if (node.parent != null) {
					if (node.parent.left == node)
						node.parent.left = null;
					if (node.parent.right == node)
						node.parent.right = null;
				}
				node = null;
				System.arraycopy(array, current, array, current - 1, count-- - current--);
				showTree(rootSon);	
				System.out.println();
			}
		};
	}
	

	
	@Override
	public void addSon(IAdressData data) {
		ProfessionNode node = new ProfessionNode(this, data);
		if(array.length == count) {
			array = Arrays.copyOf(array, count + count / 2);
		}
		count++;
		array[count-1] = node;
		//Arrays.sort(array, 0, count - 1);
		if(rootSon == null) {
			rootSon = node;
		}
		else {
			addRecursive(node, node.parent, rootSon);
		}
//		showTree(rootSon);

	}

	void showTree(ProfessionNode rootSon) {
		if(rootSon == null) 
			return; 
		showTree(rootSon.left);				
		System.out.println(rootSon);
		showTree(rootSon.right);
	}
	
	ProfessionNode addRecursive (ProfessionNode node, ProfessionNode parent, ProfessionNode root) {
		if(root == null) {
			root = node; 
			node.parent = parent;
		}
		else if(node.compareTo(root) < 0) {
			root.left = addRecursive(node, root, root.left);
		}
		else {
			root.right = addRecursive(node, root, root.right);
		}
		return root;
	}
	
	@Override
	public Component createPanel() {
		return new JEKPanel();
	}

	@Override
	public Component createSonPanel() {
		return new ProfessionPanel();
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
			return array[childIndex];
	}

	@Override
	public int getChildCount() {
		return count;
	}
}
