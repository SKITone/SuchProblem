package problemExamples;

import java.util.LinkedList;

import problemDef.Zustand;
import suchverfahren.*;

public class WPMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*WPZustand WPZ1 = new WPZustand(5,5);
		WasserProblem WPr1 = new WasserProblem(5,7,3);
		LinkedList<Zustand> successorList;
		successorList = WPr1.genNf(WPZ1);
		
		System.out.println(successorList);*/
		
		System.out.println();
		System.out.println("Breitensuche:");
		System.out.println();
		WasserProblem WPr1 = new WasserProblem(5,7,3);
		Breitensuche BS1= new Breitensuche(WPr1, 8);
		BS1.loop();
		
		System.out.println();
		System.out.println();
		System.out.println("Tiefensuche:");
		System.out.println();
		
		Tiefensuche TS1= new Tiefensuche(WPr1);
		TS1.loop();
		
		System.out.println();
		System.out.println();
		System.out.println("Tiefensuche mit begrenzter Tiefe:");
		System.out.println();
		
		Tiefensuche TS2= new Tiefensuche(WPr1, 4);
		TS2.loop();
	}

}