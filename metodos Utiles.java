public class LevelIsComplete{
	public Iterable<Integer> levelIsComplete(BinaryTree<Position<E> tree){

	}

	public void aux(BinaryTree<Position<E>> tree, int level){
		LinkedList<Position<E>> lista;
		LinkedList<Integer> out = new LinkedList<>();
		for(int i=1; i<=altura(tree, tree.root()); i++){
			lista  = new LinkedList<>();
			findLevel(i, 1, tree, tree.root(), lista);
			if(nodeIsComplete(lista, tree)) out.add(i);
		}
		return out;
	}

	public void findLevel(int levelMeta, int levelActual, BinaryTree<Position<E>> tree, Position<E> node,LinkedList<Position<E>> out){
		if(levelActual == levelMeta){
			out.add(node);
		}
		else if(levelActual < levelMeta){
			for(Position<E> child: tree.children(node)){
				findLevel(levelMeta, levelActual+1, tree, child, out);
			}
		}
	}


	public int altura(BinaryTree<Position<E>> tree, Position<E> pos){
		if(tree.isEmpty()) return 0;
		int izq,der;
		else if(tree.hasLeft(pos)){
			izq = altura(tree, tree.getLeft(pos));
		}
		else if(tree.hasRight(pos)){
			der = altura(tree, tree.getRight(pos));
		}
		return 1 + Math.max(izq, der);
	}
	public boolean nodeIsComplete(LinkedList<Position<E>> l, BinaryTree<Position<E>> tree){
		boolean b = true;
		for(Position<E> elem: l){
			b = b && (tree.hasLeft(elem) && tree.hasRight(pos))

		}
		return b;
	}
}