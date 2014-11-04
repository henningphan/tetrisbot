package Heuristic;

import tetrisGame.TetrisMap;

public abstract class Heuristic {
	protected int weight;
	public Heuristic setWeight(int weight){
		this.weight = weight;
		return this;
	}
	public int getWeight(){
		return weight;
	}
	public abstract int calculate(TetrisMap gm);
	public String toString(){
		return "Heuristic base class";
	}
}
