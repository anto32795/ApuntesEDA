public class HashTableMap<K,V> implements Map<K,V>{
	private class HashEntry<T,U> implements Entry<T,U>{
		private T Key;
		private V Value;
		private HashTableMap<K,V> myMap;

		//constructor, get, set...

		@Override
		public boolean equals(Object o){
			if(o.getClass()!=this.getClass()){
				return false;
			}
			HashTableMap<T,U> ent;
			try{
				ent = (HashTableMap<T,U>)o;
			}catch(ClassCastException ex){
				return false;
			}
			return ((ent.getKey().equals(this.key))&&(ent.getValue().equals(this.value)));
		}

	}
	private int n;
	private long scale,shift;
	private int prime,capacity; //prime p (del mod p), capacity tamaño del array
	private HashEntry<K,V>[] bucket;
	private final Entry<K,V> AVALIABLE = new HashEntry<>(null,null); //en mayuscula = constante, yo creo q es ENtry<T,U>

	public HashTableMap(int p, int cap){ //p-> primo cap ->tamaño tabla
		this.n = 0;
		this.prime = p;
		this.capacity = cap;
		this.bucket = (HashEntry<K,V>[]) new Object[this.capacity];
		Random rand = new Random();
		this.scale = rand.nextInt(prime-1)+1; //no quiero que me de 0
		this.shift = rand.nextInt(prime);
	}

	private int hasValue(K key){
		return (int)(Math.abs(key.hashCode() * this.scale + this.shift) % this.prime) % this.capacity;
	}

	/*
	put(K k, V v)
	remove(K k)
	get(K k)
	*/
	private class HashEntryIndex{
		int index;
		boolean found;
		public HashEntryIndex(int index, boolean f){
			this.index = index;
			this.found = f;
		}
	}

	private HashEntryIndex findKey(K key) throws RuntimeException{
		checkKey(key);
		int avail = -1;
		int i = hashValue(key);
		int final j = i;
		do{										// Prueba lineal?
			Entry<K,V> e = this.bucket[i];
			if(e == null){
				if(avail < 0)
					avail = i;
				break:
			}
			else if(key.equals(e.getKey()))
				return new HashEntryIndex(i,true);
			else if(e == this.AVALIABLE){
				if(avail < 0){
					avail = i;
				}
			}
			i = (i+1)%this.capacity;
		}while(i != j);
		return new HashEntryIndex(avail,false);
	}

	public V get(K key) throws RuntimeException{
		HashEntryIndex i = findKey(key);
		if(!i.found){
			return null;
		}
		return (this.bucket[i.index]).getValue();
	}

	public V remove(K key) throws RuntimeException{
		HashEntryIndex i = findKey(key);
		if(!i.found)
			return null;
		V toReturn = this.bucket[i.index].getValue();
		this.bucket[i.index] = this.AVALIABLE;
		this.n--
		return toReturn;
	}

	public V put(K key,V value) throws RuntimeException{ // Valor que sobreescribo si aplica
		HashEntryIndex i = findKey(key);
		if(i.found){
			return this.bucket[i.index].setValue(value);
		}

		// Comprobar que no supero el factor de carga lambda.
		else if(n >= this.capacity/2){
			reHash();
			i = findKey(key);
		}
		this.bucket[i.index] = new HashEntry<>(key,value);
		this.n++;
		return null;
	}

	private void reHash(){
		this.capacity = 2 * this.capacity;
		HashEntry<K,V>[] old = this.bucket;
		this.bucket = (HashEntry<K,V>[]) new Object[this.capacity];
		Random rand = new Random();
		this.scale = rand.nextInt(this.prime-1)+1;
		this.shift = rand.nextInt(this.prime);

		for(HashEntry<K,V> e: old){
			if(e != null && e != this.AVALIABLE){
				int j = findKey(e.getKey().index);
				this.bucket[j] = e;
			}
		}
	}

}