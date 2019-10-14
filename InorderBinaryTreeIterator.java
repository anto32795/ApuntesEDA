public class InorderBinaryTreeIterator<E> implements Iterator<Position<E>>{ //para entender inorden mira transparencias
	private Deque<Position<E>> nodeStack = new LinkedList<>(); //deque = bicola??
	private BinaryTree<E> tree;
	public InorderBinaryTreeIterator(BinaryTree t){
		this.tree = t;
		goToLastInLeft(this.root);//vete todo lo q puedas a la izq
		private void goToLastInLeft(Position<E> p){
			nodeStack.addFirst(p);
			while(this.tree.hasLeft(p)){
				p = this.tree.left(p);
				nodeStack.addFirst(p);
			}
		}
	}
	public boolean hasNext(){
		return (!nodeStack.isEmpty());
	}
	public Position<E> next(){
		Position<E> aux = nodeStack.removeFirst();
		if(this.tree.hasRight(aux)){
			goToLastInLeft(this.tree.right(aux));
		}
		return aux;
	}
	public void remove(){
		throw new UnsupportedOperationException("Not implemented");
	}
}