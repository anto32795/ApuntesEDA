public class DoubleLinkedList<E> implements List<E>{
	private class DNode<E> implements Position<E>{
		private DNode<E> prev,next;
		private E elem;
		private DoubleLinkedList<E> id; //matricula

		public E getElement(){
			return this.elem;
		}
		public DNode<E> getNext();

		private DNode<E> head,tail;
		private int size;

		public Position<E> addLast(E e){
			DNode<E> newNode;
			if(this.isEmpty()){
				newNode = new DNode<>(null,null,e);
				this.head = newNode;
				this.tail = newNode;
			}else{
				newNode = new DNode<>(this.prev,null,e);
				this.head = setNext(newNode);
				this.tail = newNode;
			}
			return newNode;
		}

		public E remove(Position<E> p) throws RuntimeException{
			DNode<E> node = checkPosition(p);
			E elem = node.getElement();
			if(this.head == this.tail){
				this.head = null;
				this.tail = null;
			}
			this.size--;
			return elem;
		}
	}
}