package suchverfahren;

import java.util.LinkedList;

import problemDef.SuchProblem;
import problemDef.Zustand;
import treeSearchDef.*;

import java.util.Stack;


public class Tiefensuche implements TreeSearch {
	
	Stack<Knoten> open = new Stack<Knoten>();
	SuchProblem suchproblem;
	Knoten K;
	int d_max;
	
	public Tiefensuche(SuchProblem suchproblem, int d_max) {
		this.d_max=d_max;
		this.suchproblem=suchproblem;
		open.add(new Knoten(suchproblem.getStartState()));				//Die Warteschlange open wird mit dem Wurzelknoten des Suchbaums initialisiert. 
	}
	
	@Override
	public Knoten select() {
		// TODO Auto-generated method stub
		
		Knoten knoten=open.pop();									//letzte Knoten der Liste wird gewählt, Knoten wird aus Liste gelöscht										
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
	public Knoten loop() {												//Last in First out
		// TODO Auto-generated method stub
		K= select();
		//System.out.println("test");
		if (isGoalNode(K)) {
			System.out.println(K.getState()+" "+K.getPredNode()+" "+K.getTiefe());
			System.out.println(open);
			return K;
		}
		if (K.getTiefe()<d_max) {
			update();
			return loop();
		} else {
			return loop();
		}	
	}
}

