package it.polito.tdp.food.model;

public class TestModel {

	public static void main(String[] args) {
Model m=new Model();
m.creaGrafo(1);
System.out.println(m.avviaRicorsione(2, "can"));
	}

}