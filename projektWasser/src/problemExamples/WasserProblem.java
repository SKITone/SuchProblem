package problemExamples;

import java.util.LinkedList;

import problemDef.SuchProblem;
import problemDef.Zustand;

public class WasserProblem extends java.lang.Object implements SuchProblem{	//Definition von Wasserumfuellproblemen.
	
	private int valA,valB,zm;
	
	public WasserProblem(int valA, int valB, int zm) {						//Initialisiert die Daten fuer ein Wasserumfuellproblem.
		this.valA=valA;														//maximales Fassungsvermoegen von Behaelter A
		this.valB=valB;														//maximales Fassungsvermoegen von Behaelter B
		this.zm=zm;															//Wert der Zielmenge von Wasser, die abzumessen ist
	}

	@Override
	public Zustand getStartState() {
		// TODO Auto-generated method stub
		WPZustand start = new WPZustand(0,0);
		return start;														//Returns the start
	}

	@Override
	public boolean isGoalState(Zustand state) {								//Methode prueft, ob der uebergebene Zustand das Zielkriterium erfuellt.
		// TODO Auto-generated method stub
		WPZustand current= (WPZustand) state;								//state - Der zu pruefende Zustand.
		return (current.k1 == zm || current.k2 == zm);						//true, falls der Zustand ein Zielzustand ist, sonst false
	}

	@Override
	public LinkedList<Zustand> genNf(Zustand state) {						//Methode, die alle Zustaende generiert, die in einem Schritt von einem gegebenen Zustand aus erreichbar sind.
		// TODO Auto-generated method stub
		
		WPZustand z = (WPZustand) state;									//state - Zustand, dessen Nachfolger bestimmt werden sollen
		LinkedList<Zustand> successor= new LinkedList <Zustand>();
		
		if(z.k1 > 0) {
			successor.add(new WPZustand(0,z.k2));							//leere k1
		}
		if(z.k2 > 0) {
			successor.add(new WPZustand(z.k1,0));							//leere k2
		}
		if(z.k1 < valA) {
			successor.add(new WPZustand(valA,z.k2));						//fülle k1
		}
		if(z.k2 < valB) {
			successor.add(new WPZustand(z.k1,valB));						//fülle k2
		}
		if(z.k1 > 0 && z.k2 < valB) {
			successor.add(umfuellen1_2(z));									//fülle k1 in k2
		}
		if(z.k2 > 0 && z.k1 < valA) {
			successor.add(umfuellen2_1(z));									//fülle k2 in k1
		}
		return successor;													//Liste mit den Nachfolgern von state
	}
	
	private WPZustand umfuellen1_2(WPZustand z) {
		int z1,z2;
		
		if(z.k1 > (valB-z.k2)) {											//k1 überfüllt k2
			z2=valB;														//k2 voll
			z1=z.k1-(valB-z.k2);											//k1 mit Rest
		}
		else{
			z2=z.k1 +z.k2;													//k1 passt in k2
			z1=0;
		}
		return new WPZustand(z1,z2);
		
	}
	private WPZustand umfuellen2_1(WPZustand z) {
		int z1,z2;
		if(z.k2 > (valA-z.k1)) {
			z1=valA;
			z2=z.k2-(valA-z.k1);
		}
		else{
			z2=z.k1 +z.k2;
			z1=0;
		}
		return new WPZustand(z1,z2);
	}
	
}
