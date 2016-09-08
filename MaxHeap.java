class MaxHeap{

	private int[] harr;
	private int capacity;
	private int tail;

	public MaxHeap(int n){
		harr = new int[n];
		capacity = n;
		tail=-1;
	}

	public  int parent(int i){	
		return (i-1)/2;
	}

	public  int getLeft(int i){
		return (2*i)+1;
	}
	public  int getRight(int i){
		return (2*i)+2;
	}

	public int getMini(){
		return harr[0];
	}
	
	public int min(int l,int r){

		if(harr[l]<harr[r])
			return l;
		return r;
	}

	public void downheap(int index){

		while(index<tail){
	
			int lindex = 2*index +1;
			if(lindex > tail)
				break;

			int rindex = 2*index +2;
			if(rindex > tail){
				// compare with lchild
				if(harr[index] > harr[lindex]){
					swap(index,lindex);
				}
				break;
			}else{
				// find min of two child and then compare
				int cindex = min(lindex,rindex);
				swap(index,cindex);
				index =cindex;
			}

		}		
	}

	public int extractMin(){

		if(tail <0){
			System.out.println("Empty heap");
			return -1;
		}
			
		int min = getMini();
	
		swap(0,tail);
		harr[tail] = 0;
		tail = tail-1;
		if(tail > 0)
			downheap(0);		
		return min;
	}

	public void swap(int a,int b){
		int temp = harr[a];
		harr[a] =harr[b];
		harr[b] = temp;
	}

	public void upheap(int index){
	
		while(index!= 0 && harr[parent(index)] > harr[index]){
			swap(parent(index),index);
			index = parent(index);
		}

	}

	public void insert(int newVal){
		harr[++tail] =newVal;
		if(tail >0)
			upheap(tail);
	}

	public void heapify(MaxHeap h, int index){

		int lindex = 2*index+1;
		int rindex = 2*index+2;
		int largest = index;		
	
		if(lindex<=h.tail && harr[lindex] > harr[largest]){
			largest = lindex;
		}
		if(rindex<= h.tail && harr[rindex] > harr[largest])
			largest= rindex;

		if(largest!= index){
			swap(index,largest);
			heapify(h,largest);
		}

	}
	
	public static MaxHeap buildHeap(int[] arr,int length){

		MaxHeap h = new MaxHeap(length);
		h.harr = arr;
		h.capacity = length;
		h.tail = length-1;
		for(int i= (length-2)/2; i>=0; i--){
			h.heapify(h,i);
		}
		return h;
	}

	public void heapSort(MaxHeap h){
		for(int i=0;i<h.capacity;i++){
			int max = h.harr[0];
			swap(0,h.tail);
			h.tail--;
			h.heapify(h,0);
		}
	}
	
	public static void main(String []args)throws Exception{
	
		int arr[] = {12, 11, 13, 5, 6, 7};
		MaxHeap h =buildHeap(arr,arr.length);
		
		h.heapSort(h);		

		for(int i=0;i<arr.length;i++)
			System.out.print(h.harr[i]+" ");
	}
}
