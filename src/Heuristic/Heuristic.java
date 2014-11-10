package Heuristic;

import tetrisGame.TetrisMap;

public abstract class Heuristic {
	protected double weight;
	public Heuristic setWeight(double weight){
		this.weight = weight;
		return this;
	}
	public double getWeight(){
		return weight;
	}
	public abstract double calculate(TetrisMap gm);
	public String toString(){
		return "Heuristic base class";
	}
}
