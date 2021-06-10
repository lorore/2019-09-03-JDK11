package it.polito.tdp.food.model;

public class Adiacenza {
	
	String p1;
	String p2;
	Integer peso;
	public Adiacenza(String p1, String p2, Integer peso) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Adiacenza [p1=" + p1 + ", p2=" + p2 + ", peso=" + peso + "]";
	}
	
	

}
