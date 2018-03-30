package gametheory.util;

import java.util.*;

public class ProportionalRandom {
	Random random;
	Map<Object, Leaf> leafs;
	RootNode tree;
	
	public ProportionalRandom() {
		random = new Random();
		leafs = new HashMap<Object, Leaf>();
		tree = new RootNode();
	}
	
	public void addObject(Object object, double weight) {
		Leaf leaf = new Leaf(object, weight);
		leafs.put(object, leaf);
		tree.addLeaf(leaf);
	}
	public void setWeight(Object object, double weight) {
		leafs.get(object).setWeight(weight);
	}
	public double getWeight(Object object) {
		return leafs.get(object).getWeight(); 
	}

	public Object getRandomElement() {
		return tree.getElement(random.nextDouble() * tree.getWeight());
	}
	
	static interface TreeElement {
		public void updateWeight();
		public double getWeight();
		public Object getElement(double slice);
		public void addLeaf(Leaf leaf);
	}
	static interface TreeNode extends TreeElement {
		public void setChild(TreeElement child);
	}

	static class RootNode implements TreeNode {
		double weight;
		TreeElement child;
		
		public RootNode() {
			weight = 0.0;
			Leaf leaf = new Leaf(null, 0.0);
			leaf.setParent(this);
			child = leaf;
		}
		public void updateWeight() {
			this.weight = child.getWeight();
		}
		public double getWeight() {
			return weight;
		}
		public Object getElement(double slice) {
			return child.getElement(slice);
		}
		public void addLeaf(Leaf leaf) {
			child.addLeaf(leaf);
		}
		public void setChild(TreeElement child) {
			this.child = child;
		}
	}

	static class BranchNode implements TreeNode {
		double weight;
		TreeElement leftChild, rightChild;
		TreeElement parent;

		public BranchNode(TreeElement leftChild, TreeElement rightChild) {
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		public void updateWeight() {
			this.weight = leftChild.getWeight() + rightChild.getWeight();
			parent.updateWeight();
		}
		public double getWeight() {
			return weight;
		}
		public Object getElement(double slice) {
			slice -= leftChild.getWeight();
			if (slice < 0)
				return leftChild.getElement(-slice);
			else
				return rightChild.getElement(slice);
		}
		public void addLeaf(Leaf leaf) {
			TreeElement child = rightChild;
			rightChild = leftChild;
			leftChild = child;
			leftChild.addLeaf(leaf);
		}
		public void setParent(TreeElement parent) {
			this.parent = parent;
		}
		public void setChild(TreeElement child) {
			leftChild = child;
		}
	}

	static class Leaf implements TreeElement {
		double weight;
		Object object;
		TreeNode parent;
		
		public Leaf(Object object, double weight) {
			this.object = object;
			this.weight = weight;
		}
		public void setParent(TreeNode parent) {
			this.parent = parent;
		}
		public void setWeight(double weight) {
			this.weight = weight;
			updateWeight();
		}
		public void updateWeight() {
			parent.updateWeight();
		}
		public double getWeight() {
			return weight;
		}
		public Object getElement(double slice) {
			return object;
		}
		public void addLeaf(Leaf leaf) {
			BranchNode node = new BranchNode(this, leaf);
			node.setParent(parent);
			parent.setChild(node);
			this.setParent(node);
			leaf.setParent(node);
			node.updateWeight();
		}
	}	
}
