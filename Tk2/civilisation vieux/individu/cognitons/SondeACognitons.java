package civilisation.individu.cognitons;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SondeACognitons {

	ArrayList<Integer[]> n;
	Class cogniton;
	
	public SondeACognitons(String pathCogniton)
	{
		n = new ArrayList<Integer[]>();
		try {
			cogniton = Class.forName(pathCogniton);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Sonde a cogniton : " +cogniton.getName() + " prete!");

	}

	public void activer() {
		
	try {
		//System.out.println("activation");

		n.add(new Integer[20]);
		//System.out.println("tableau ajoute");

		Field field = cogniton.getField("nInstances");
		//System.out.println("Field recupere");

		
		Object array;
		try {
			array = field.get(cogniton);
			int length = Array.getLength(array);  
			//System.out.println("longueur : " +length);
			for (int i = 0; i < length; i++)  
			{  
			    Object element = Array.get(array, i);  
				n.get(n.size()-1)[i] = (Integer) element;
				//System.out.println("element  : " +i);

			}  		

			
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  

		

		
		
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println("fin activation");

	}

	public ArrayList<Integer[]> getN() {
		return n;
	}

	public Class getCognitonEvalue(){
		return cogniton;
	}
	
	
}
