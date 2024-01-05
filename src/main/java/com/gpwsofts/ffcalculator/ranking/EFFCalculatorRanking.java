package com.gpwsofts.ffcalculator.ranking;

/**
 * Expose tous les types de classement
 * @since 1.0.0
 */
public enum EFFCalculatorRanking {
	U17("nuage-u17.csv"),
	//U19("nuage-u19.csv"), le classement national U19 na pas lieu d'etre
	ALL("nuage-all.csv");
	
	public final String resource;

    private EFFCalculatorRanking(String resource) {
        this.resource = resource;
    }

	public String getResource() {
		return resource;
	}
    
}
