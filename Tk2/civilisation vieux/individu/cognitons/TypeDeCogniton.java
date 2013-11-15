package civilisation.individu.cognitons;

import java.awt.Color;

public enum TypeDeCogniton {

	    BELIEF (Color.ORANGE), MEME(Color.PINK), PERCEPT(Color.GREEN), SKILL(Color.CYAN), TRAIT(Color.MAGENTA);
	    
	    private final Color couleur;   /*La couleur associée au cogniton, pour l'interface*/
	    
	    TypeDeCogniton(Color couleur) {
	        this.couleur = couleur;
	    }
	    
	    
	    
	    public static TypeDeCogniton toType(String s){
			if (s.equals("BELIEF")){
				return TypeDeCogniton.BELIEF;
			}
			else if (s.equals("MEME")){
				return TypeDeCogniton.MEME;
			}
			else if (s.equals("PERCEPT")){
				return TypeDeCogniton.PERCEPT;
			}
			else if (s.equals("SKILL")){
				return TypeDeCogniton.SKILL;
			}
			else
				return TypeDeCogniton.TRAIT;
			}
	    
	    


	    public Color getCouleur() { return couleur; }
	    
	    
	    }

