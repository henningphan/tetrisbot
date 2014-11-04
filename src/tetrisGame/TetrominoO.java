package tetrisGame;

import java.util.LinkedList;
import java.util.Vector;

import tetrisGame.TetrisMap.Block;

public class TetrominoO extends TetrominoAbs{
	public TetrominoO(){
		super.map = new Vector<>();
		for( int i=0; i<2; ++i){
			LinkedList<Block> list = new LinkedList<>();
			list.add(Block.Fill);
			list.add(Block.Fill);
			map.add(list);
		}
		super.row = 2;
		super.col = 2;
	}
	@Override
	public int getSides() {
		return 1;
	}
	public String toString(){
		return "Tetris piece: O";
	}
		
}
