package fr.servcesimmatriculation.model;

public enum Nature {
	PersonnePysique("Personne Pysique"), PersonneMorale("Personne Morale");
	String longName;

	Nature(String longName) {
		this.longName = longName;
	}

	public String getLongName() {
		return longName;
	}
}
