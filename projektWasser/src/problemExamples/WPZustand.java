package problemExamples;

import problemDef.Zustand;

/**
 * Defintion der Zustaende fuer Wasserumfuellprobleme.
 */
public class WPZustand extends Zustand{						//Defintion der Zustaende fuer Wasserumfuellprobleme.
	
	int k1;
	int k2;
	
	public WPZustand(int k1, int k2) {
		this.k1=k1;
		this.k2=k2;
	}
	
	public int getContA() { 								//Liefert den aktuellen Fuellstand von Behaelter A
		return k1;											//Wert des Fuellstands in Litern
	}

	public int getContB() {
		return k2;
	}
	
/*	public boolean equals(java.lang.Object o) {				//Overrides: equals in class java.lang.Object
		return ;
	}
*/
	public String toString(){								//Overrides: String in class java.lang.Object
		String str;
		str = "<" + k1 + "," + k2 + ">";
		return str;
	}

}
