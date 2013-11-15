package civilisation.inventaire;

import civilisation.individu.cognitons.Belief;

/** 
 * Classe abstraite reprŽsentant un objet que peut possŽder un agent
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public abstract class ObjetInventaire {

	protected static int CoutBois = 0;
	protected static int CoutMetal = 0;
	protected static int CoutViande = 0;
	
	public ObjetInventaire()
	{
		
	}
	
	/**
	 * Fourni la valeur nutritive. Doit �tre rŽimplementŽe si l'objet peut �tre mangŽ.
	 * @return la valeur nutritive de l'objet (-1 par dŽfaut)
	 */
	public int getValeurNutritive()
	{
		return -1;
	}
	
	/**
	 * Les cognitons declenchŽs par cet objet
	 * @return tableau de string reprŽsentant les cognitons
	 */
	public String[] cognitonsLies()
	{
		return null;
	}
	
	public String getPathToIcon()
	{
		return null;
	}
	
	public boolean is(String s)
	{
		return s == "objet";
	}
	
	public int getCoutBois()
	{
		return CoutBois;
	}
	
	public int getCoutMetal()
	{
		return CoutMetal;
	}
	
	public int getCoutViande()
	{
		return CoutViande;
	}
}
