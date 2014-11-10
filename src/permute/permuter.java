package permute;

import java.util.Vector;

public class permuter {
	// TODO doesnt increment the next element properly
	public static Vector<Integer> next(int limit, Vector<Integer> vector){
		vector.set(0, vector.get(0)+1);
		for(int i= 0; i<vector.size(); ++i){
			if( vector.get(i)>= limit){
				vector.set(i, 0);
				try{
					vector.set(i+1, vector.get(i+1)+1);
				}catch(IndexOutOfBoundsException e){
				}
			}
		}
		return vector;
	}
	public static boolean hasNext(int limit, Vector<Integer> vector){
		for(Integer i: vector){
			if( i != limit-1){
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Vector<Integer> vec = new Vector<>();
		vec.add(0);
		vec.add(0);
		vec.add(0);
		System.out.println(vec);
		for( int i = 0; i<10; ++i){
			System.out.println(hasNext(2, vec));
			next(2, vec);
			System.out.println(vec);
		}

	}

}
