public class LinkedBinarySearchTree{
	private Position<E> treeSearch(E value,Position<E> pos){
		E curValue = pos.getElement();
		int comp = this.comparator.compare(value,curValue);
		if(comp<0 && this.binTree.hasLeft(pos)){
			return treeSearch(value,this.binTree.left(pos));
		}
		else if(comp > 0 && this.binTree.hasRight(pos)){
			return treeSearch(value,this.binTree.right(pos));
		}
		return pos;
	}
	

	public Position<E> insert(E value){
		if(this.binTree.isEmpty()){
			return this.binTree.addRoot(value);
		}
		Position<E> insPos = treeSearch(value,this.binTree.root());
		while(this.comparator.compare(value,insPos.getValue())==0 && !this.binTree.isLeaf(insPos) && this.binTree.hasRight(insPos)){	// son iguales
			//while(!this.binTree.isLeaf(insPos) && this.binTree.hasRight(insPos)){
				insPos = treeSearch(value,this.binTree.right());
			//}
		}
		Position<E> retPos = null;
		if(this.comparator.compare(value,intPos.getValue())<0){
			retPos = this.binTree.insertLeft(intPos,value);
		}else{
			retPos = this.binTree.insertRight(intPos,value);
		}
		return retPos;
	}
}