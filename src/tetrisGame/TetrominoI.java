package tetrisGame;

import java.util.Vector;
import java.util.LinkedList;

import tetrisGame.TetrisMap.Block;

public class TetrominoI extends TetrominoAbs{
	
	public TetrominoI(){
		super.map = new Vector<LinkedList<Block>>();
		for(int i=0; i< 4; ++i){
			LinkedList<Block> list = new LinkedList<>();
			list.add(Block.Fill);
			map.add(list);
		}
		super.row=1;
		super.col=4;
		
	}
	@Override
	public int getSides() {
		return 2;
	}
	public String toString(){
		return "Tetris piece: I";
	}

}
