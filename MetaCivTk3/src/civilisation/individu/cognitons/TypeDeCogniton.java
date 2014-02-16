package civilisation.individu.cognitons;

import java.awt.Color;

public enum TypeDeCogniton {

	    BELIEF (new Color(200,173,127)),
	    MEME(new Color(253,191,183)),
	    PERCEPT(new Color(190,245,116)),
	    SKILL(new Color(204,204,255)),
	    TRAIT(new Color(204,204,204)),
	    CLOUD(Color.BLACK);
	    
	    private final Color couleur;   /*Cogniton color*/
	    
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
			else if (s.equals("TRAIT")){
				return TypeDeCogniton.TRAIT;
			}
			else {
				return TypeDeCogniton.CLOUD;
			}
		}
	    
	    


	    public Color getCouleur() { return couleur; }
	    
	    
	    }

