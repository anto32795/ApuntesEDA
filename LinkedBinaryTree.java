public class LinkedBinaryTree<E> implements BinaryTree<E>{
	private class BTNode<E> implements Position<E>{
		private E element;
		private BTNode<E> parent;
		private BTNode<E> left,right;
		private LinkedBinaryTree<E> myTree;
		//get y set y getElement(position)
	}
	private int size;
	private BTNode<E> root;

	public Position<E> parent(Position<E> p) throws RuntimeException{
		BTNode<E> node = checkPosition(p); // Comprueba que p no es null y que p es instanceof BTNode y "matricular el nodo" con resp a arbol
		BTNode<E> parent = node.getParent();
		if(parent == null){
			throw new RuntimeException("No parent");
		}
		return parent;
	}
	public Position<E> insertRight(Position<E> p,E e)throws RuntimeException{
		BTNode<E>node = checkPosition(p);
		BTNode<E> rightNode = node.getRight();
		if(rightNode != null){
			throw new RuntimeException("Node already has right child");
		}
		BTNode<E> newNode = new BTNode<>(e,node,null,null,this);//node es su padre, hijos null,matricula this
		node.setRight(newNode);
		this.size++;
		return newNode;
	}
	public Position<E> sibling(Position<E>p) throws RuntimeException{
		BTNode<E> node = checkPosition(p);
		BTNode<E> parent = node.getParent(); //this.getParent(p) tambien vale
		if(parent != null){
			BTNode<E> sibNode;
			BTNode<E> leftNode = parent.getLeft();
			if(node == leftNode){ //si yo soy el nodo izquierdo, devuelvo el derecho
				sibNode = parent.getRight();
			}else{
				sibNode = parent.getLeft();
			}
			if(sibNode != null){
				return sibNode;
			}
		}
		throw new RuntimeException("No sibling");
	}
	public void remove(Position<E>p) throws RuntimeException{
		BTNode<E> node = checkPosition(p);
		//Position<E> p1 = this.getLeft(p);  no paso el checkPosition porq es mio y no del user
		BTNode<E> left = node.getLeft();
		BTNode<E> right = node.getRight();
		if(left != null && right != null){
			throw new RuntimeException("Cannot remoe a node with two chihldren");
		}
		BTNode<E> child;
		if(left != null){
			child = left;
		}else if(right != null){
			child = right;
		}else{
			child = null; //en java no hace falta, ya lo hace por defecto
		}

		//Comprobar si es la raiz
		if(node == this.root){
			if(child != null){//corregir ptr child
				child.setParent(null);
			}
			this.root = child;
		}else{
			BTNode<E> parent = node.getParent();
			if(node == parent.getLeft()){
				parent.setLeft(child);
			}else{
				parent.setRight(child);
			}
			if(child != null){
				child.setParent(parent);
			}
		}
		node.setMyTree(null);//quitar matricula
		this.size--;
	}
}

//Ahroa una funcion main

public static void main(){
	BinaryTree<String> bt= new LinkedBinaryTree<>();
	Position<String> pr = bt.addRoot("A");
	bt.insertLeft("B");
	Position<String> p1 = pr.insertRight("C");
	pr.remove(p1);
	bt.hasLeft(p1);
}