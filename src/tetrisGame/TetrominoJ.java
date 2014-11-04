package tetrisGame;

import java.util.LinkedList;
import java.util.Vector;

import tetrisGame.TetrisMap.Block;

public class TetrominoJ extends TetrominoAbs{
	
	public TetrominoJ(){
		super.map = new Vector<LinkedList<Block>>();
		LinkedList<Block> list = new LinkedList<>();
		list.add(Block.Fill);
		list.add(Block.Fill);
		map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		map.add(list);
		super.row = 2;
		super.col = 3;
		
	}
	@Override
	public int getSides() {
		return 4;
	}
	public String toString(){
		return "Tetris piece: J";
	}

}
