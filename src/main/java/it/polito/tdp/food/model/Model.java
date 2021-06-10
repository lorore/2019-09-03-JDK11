package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
private Graph<String, DefaultWeightedEdge> graph;
private FoodDao dao;
private List<String> result;
public Model() {
	this.dao=new FoodDao();
}

public String creaGrafo(int c) {
	graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	//aggiungo vertici
	List<String> vertici=dao.getVertici(c);
	Graphs.addAllVertices(graph, vertici);
	//System.out.println(graph.vertexSet().size());
	List<Adiacenza> archi=dao.getArchi(c);
	for(Adiacenza a: archi) {
		if(graph.containsVertex(a.getP1()) && graph.containsVertex(a.getP2())) {
			Graphs.addEdge(graph, a.getP1(), a.getP2(), a.getPeso());
		}
	}
//	System.out.println(graph.edgeSet().size());
	return "Grafo creato. Num vertici: "+this.graph.vertexSet().size()+" Num archi: "+this.graph.edgeSet().size();
	
}

public List<PorzioneConnessa> getVicini(String p) {
List<String> vicini=Graphs.neighborListOf(graph, p);
List<PorzioneConnessa> result=new ArrayList<>();
for(String vicino: vicini) {
double peso=graph.getEdgeWeight(graph.getEdge(vicino, p));
result.add(new PorzioneConnessa(vicino, peso));
}

return result;

}

public List<String> ritornaVertici(){
	List<String> result=new ArrayList<>(this.graph.vertexSet());
	Collections.sort(result);
	return result;
}

public List<String> avviaRicorsione(int N, String p) {
	List<String> parziale=new ArrayList<>();
	//List<String> risultato=new ArrayList<>();
	this.result=null;
	parziale.add(p);
	this.ricorsione(N, 1, parziale,0);
	return this.result;
	
	
}


public void ricorsione(int N, int livello, List<String> parziale,  double max) {
	//lunghezza in termini di numero di archi considero
	if((parziale.size()-1)==N) {
		double pesoParziale=this.calcolaPeso(parziale);
		if(pesoParziale>max) {
		this.result=new ArrayList<>(parziale);
			System.out.println(this.result);
			max=pesoParziale;
			
		}
	}else {
		String p=parziale.get(parziale.size()-1);
		List<String> vicini=Graphs.neighborListOf(graph, p);
		for(String s: vicini) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				System.out.println(parziale);
				this.ricorsione(N, livello+1, parziale, max);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	
}

private double calcolaPeso(List<String> parziale) {
	int lunghezza=parziale.size();
	int i=0;
	double pesoTot=0.0;
	while(i<lunghezza-1) {
		double peso=this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i), parziale.get(i+1)));
		pesoTot+=peso;
		i++;
	}
	return pesoTot;
	
	
}
	
}
