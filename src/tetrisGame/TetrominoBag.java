package tetrisGame;

import java.util.Random;
import java.util.Vector;

import tetrisGame.Tetromino.Type;

public class TetrominoBag {
	private Random rng;
	private Vector<Type> bag;
	private int modulo;
	/**
	 * Uses a 0 as seed
	 */
	public TetrominoBag() {
		this(0);
	}
	
	public TetrominoBag(int seed){
		rng = new Random(seed);
		bag = new Vector<>();
		modulo =0;
	}
	public TetrominoAbs next(){
		if( modulo ==0 ){
			for(Type t: Type.values()){
				bag.add(t);
			}
			modulo = Type.values().length;
		}
		int index = rng.nextInt(modulo);
		--modulo;
		Type t = bag.remove(index);
		switch(t){
		case O:
			return new TetrominoO();
		case T:
			return new TetrominoT();
		case I:
			return new TetrominoI();
		case J:
			return new TetrominoJ();
		case L:
			return new TetrominoL();
		case S:
			return new TetrominoS();
		case Z:
			return new TetrominoZ();
		default:
			throw new IllegalArgumentException("There is no case to handle: " + t);
		}
	}
	public static void main(String[] args) {
		TetrominoBag bag = new TetrominoBag();
		for(int i=0; i<21; ++i){
			System.out.println(bag.next().getClass().getName());
		}

	}

}
