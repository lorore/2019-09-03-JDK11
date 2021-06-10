package it.polito.tdp.food.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
private Graph<String, DefaultWeightedEdge> graph;
private FoodDao dao;

public Model() {
	this.dao=new FoodDao();
}

public void creaGrafo(int c) {
	graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	//aggiungo vertici
	List<String> vertici=dao.getVertici(c);
	Graphs.addAllVertices(graph, vertici);
	System.out.println(graph.vertexSet().size());
	List<Adiacenza> archi=dao.getArchi(c);
	for(Adiacenza a: archi) {
		if(graph.containsVertex(a.getP1()) && graph.containsVertex(a.getP2())) {
			Graphs.addEdge(graph, a.getP1(), a.getP2(), a.getPeso());
		}
	}
	System.out.println(graph.edgeSet().size());
	
}


	
}
