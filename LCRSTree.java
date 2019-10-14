public class LCRSTree<E> implements Tree<E>{
	private class LCRSNode<E> implements Position<E>{
		private E element;
		private LCRSNode<E> parent;
		private LCRSNode<E> left,right; //hijo y hermano
		private LCRSTree<E> myTree;

		//TODO: getter setters, aunque como la clase es privada y solo la uso yo, podrias ponerlos publicos.
	}
	private int size;
	private LCRSNode<E> root;
}