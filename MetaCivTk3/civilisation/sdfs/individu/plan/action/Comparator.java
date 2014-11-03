package civilisation.individu.plan.action;

import civilisation.individu.cognitons.TypeDeCogniton;

public enum Comparator {

	greater,greaterEqual,equal,smallerEqual,smaller;
	
	
	
	public boolean compare(Double a , Double b) {
		if (this == greater) {
			return a > b;
		}
		else if (this == greaterEqual) {
			return a >= b;
		}
		else if (this == equal) {
			return a == b;
		}
		else if (this == smallerEqual) {
			return a <= b;
		}
		else if (this == smaller) {
			return a < b;
		}
		
		return false;
	}
	
	public boolean compare(float a , float b) {
		if (this == greater) {
			return a > b;
		}
		else if (this == greaterEqual) {
			return a >= b;
		}
		else if (this == equal) {
			return a == b;
		}
		else if (this == smallerEqual) {
			return a <= b;
		}
		else if (this == smaller) {
			return a < b;
		}
		
		return false;
	}
	
    public static Comparator toComparator(String s){
		if (s.equals(">")){
			return greater;
		}
		else if (s.equals(">=")){
			return greaterEqual;
		}
		else if (s.equals("==")){
			return equal;
		}
		else if (s.equals("<=")){
			return smallerEqual;
		}
		else if (s.equals("<")){
			return smaller;
		}
		else {
			return null;
		}
	}
    
    public String toSymbol() {
    	
		if (this == greater) {
			return ">";
		}
		else if (this == greaterEqual) {
			return ">=";
		}
		else if (this == equal) {
			return "==";
		}
		else if (this == smallerEqual) {
			return "<=";
		}
		else if (this == smaller) {
			return "<";
		}
    
		return null;
    }
    
	
}



