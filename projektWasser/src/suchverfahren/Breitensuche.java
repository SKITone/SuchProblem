package suchverfahren;

import treeSearchDef.*;
import problemDef.*;

import java.util.LinkedList;


public class Breitensuche implements TreeSearch{

	LinkedList<Knoten> open = new LinkedList<Knoten>();
	LinkedList<Knoten> close = new LinkedList<Knoten>();
	SuchProblem suchproblem;
	Knoten K;
	int d_max;
	
	public Breitensuche(SuchProblem suchproblem,int d_max) {
		this.d_max=d_max;
		this.suchproblem=suchproblem;
		open.add(new Knoten(suchproblem.getStartState()));				//Die Warteschlange open wird mit dem Wurzelknoten des Suchbaums initialisiert. 
	}
	
	@Override
	public Knoten select() {
		// TODO Auto-generated method stub
		
		Knoten knoten=open.poll();									//letzte Knoten der Liste wird gewählt, Knoten wird aus Liste gelöscht										
		return knoten;
	}

	@Override
	public boolean isGoalNode(Knoten k) {
		// TODO Auto-generated method stub	
		
		return (suchproblem.isGoalState(K.getState()));			//Das Zielkriterium des zu lösenden Suchproblems wird auf den Zustand state von K angewendet.
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		LinkedList<Zustand> successor= suchproblem.genNf(K.getState());	//Es werden alle Folgezustände des Zustands state von K erzeugt. Dies geschieht durch Anwendung aller möglichen Aktionen auf state.
		while (!successor.isEmpty()) {
			Zustand zustand=successor.pop();
			open.add(K.succNode(zustand));								//Für jeden Folgezustand state_new wird ein neuer Knoten im Suchbaum erzeugt
		}
		
	}

	@Override
	public Knoten loop() {												//First in First out
		// TODO Auto-generated method stub
		K= select();
		//System.out.println("test");
		if (isGoalNode(K)) {
			System.out.println("Zustand: "+K.getState()+", vorheriger Knoten: "+K.getPredNode()+", Tiefe: "+K.getTiefe());
			System.out.println("offene Knoten: "+open);
			System.out.println("abgeschlossene Knoten: "+close);
			return K;
		}
		if (K.getTiefe()==9 || K.getTiefe()==d_max) {
			System.err.println("zu Tief - Abbruch");
			return null;
		}

		close.add(K);
		update();	
		return loop();
	}
	
	public LinkedList<Knoten> getabgelaufeneKnoten(){
		return close;
	}
}
