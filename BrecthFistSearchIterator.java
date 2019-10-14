public class BrecthFirstSearchIterator<E> implements Iterable<Position<E>>{
	private Queue<Position<E>> nodeQueue;
	private Tree<E> tree;
	public BrecthFirstSearchIterator(Tree<E> tree){   //recorre el arbol en anchura, luego usa colas
		this.tree = tree;
		this.nodeQueue = new LinkedList<>();
		this.nodeQueue.add(this.tree.root);
	}
	public BrecthFirstSearchIterator(Tree<E> tree,Position<E>p){
		this.tree = tree;
		this.nodeQueue = new LinkedList<>();
		this.nodeQueue.add(p);
	}
	public boolean hasNext(){
		return(!nodeQueue.isEmpty());
	}
	public Position<E> next(){
		Position<E> aux = this.nodeQueue.podFirst(); //desencolado
		for(Position<E>node: this.tree.children(aux)){
			this.nodeQueue.addLast(node);
		}
		return aux;
	}
	public Iterable<?extends Position<E>> children(Position<E>p) throws RuntimeException{
		TreeNode <E> node = new checkPosition(p);
		return node.children();
	}
	public void remove(Position<E>p)throws RuntimeException{
		TreeNode<E>node = checkPosition(p);
		int cont=0;
		node.setMyTree = null;
		if(node.getParent()==null){
			this.root = null;
			this.size = 0;
		}else{
			TreeNode<E> parent = node.getParent();
			parent.getChildren().remove(node);
			BrecthFirstSearchIterator<E> it= new BrecthFirstSearchIterator<>();
			while(!hasNext()){
				Position<E> ptr= it.next();  //ptr lo añadi yo, en los apuntes no salia nada en lugar d ptr.
				cont++;
			}
		}
	}
}