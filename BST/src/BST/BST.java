package BST;

public class BST {
	private Node root;
	int size = 0;
	
	private class Node{
		private int key;
		private Node left_child;
		private Node right_child;
		private Node parent;
		Node(int key){
			this.key = key;
			this.left_child = null;
			this.parent = null;
			this.right_child = null;
		}
	}
	
	public void insert(int key){
		if(this.search(key) != null){
			System.out.println(key + " is already in the tree");
			return;
		}
		Node nn = new Node(key);	// assume the data is same as key
		
		if(root == null){
			root = nn;
			size++;
			return;
		}
		Node temp = root;
		while(temp != null){//find node's position and add
			if(key < temp.key){
				if(temp.left_child != null)
					temp = temp.left_child;
				else{
					temp.left_child = nn;
					nn.parent = temp;
					break;
				}
			}
			else{
				if(temp.right_child != null)
					temp = temp.right_child;
				else{
					temp.right_child = nn;
					nn.parent = temp;
					break;
				}
			}
		}
		size++;
		
	}
	public void delete(int key){
		Node temp = search(key);
		if(temp != null){
			if(temp.left_child == null && temp.right_child == null){//temp has 0 child
				if(temp.parent.left_child == temp){//temp is leftchild of temp.parent
					temp.parent.left_child = null;
				}
				else{//temp is rightchild of temp.parent
					temp.parent.right_child = null;
				}
				temp.parent = null;
			}
			else if(temp.right_child == null && temp.left_child != null){//temp has only left child
				if(temp.parent.left_child == temp)//temp is leftchild of temp.parent
					temp.parent.left_child = temp.left_child;
				else//temp is rightchild of temp.parent
					temp.parent.right_child = temp.left_child;
				
				temp.left_child.parent = temp.parent;
				temp.parent = null;
				temp.left_child = null;	
				
			}
			else if(temp.right_child != null && temp.left_child == null){//temp has only right child
				if(temp.parent.left_child == temp)
					temp.parent.left_child = temp.right_child;
				else
					temp.parent.right_child = temp.right_child;
				
				temp.right_child.parent = temp.parent;
				temp.parent = null;
				temp.right_child = null;
			}
			else if(temp.left_child != null && temp.right_child != null){//temp has two child
				Node temp2 = temp.left_child;
				while(temp2 != null){
					if(temp2.right_child != null)
						temp2 = temp2.right_child;
					else
						break;
				}
				temp.key = temp2.key;
				if(temp2.parent.left_child == temp2){
					temp2.parent.left_child = null;	
				}
				else{
					temp2.parent.right_child = null;
				}
				temp2.parent = null;
			}
			size--;
		}
		else{
			System.out.println("Sorry! The key is not in tree!");
		}
	}

	public Node search(int key){
		Node temp = root;
		for(int i = 0; i < size; i++){
			if(temp.key == key){
				return temp;
			}
			else{
				if(key < temp.key && temp.left_child != null)
					temp = temp.left_child;
				else if(key > temp.key && temp.right_child != null){
					temp = temp.right_child;
				}
			}
		}
		return null;
		
	}
	public void printPreorder(Node node){
		System.out.printf("%d ",node.key);
		if(node.left_child != null){
			printPreorder(node.left_child);
		}
		if(node.right_child != null){
			printPreorder(node.right_child);
		}
	}
	public void printInorder(Node node){
		if(node.left_child != null)
			printInorder(node.left_child);
		System.out.printf("%d ",node.key);
		if(node.right_child != null)
			printInorder(node.right_child);
	}
	
	public void printPostorder(Node node){
		if(node.left_child != null)
			printPostorder(node.left_child);
		if(node.right_child != null)
			printPostorder(node.right_child);
		System.out.printf("%d ",node.key);
	}
	public Node getRoot(){
		return root;
	}
}
