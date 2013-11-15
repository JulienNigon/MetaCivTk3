package civilisation;

import edu.turtlekit2.kernel.agents.Turtle;
import madkit.kernel.Message;

public class AllMessage extends Message {

	private String subject;
	private Object message;
	private Turtle sender;
    
    public AllMessage(){
        subject = new String();
        message = null;
        sender = null;
    }
    
    public AllMessage(String sujet, Object mess, Turtle send){
        subject = sujet;
        message = mess;
        sender = send;
    }
    
    public void add(String sujet, Object mess){
        subject = sujet;
        message = mess;
    }
    
    public void addSubject(String sujet)
    {
    	subject = sujet;
    }
    
    public void addMessage(Object mess)
    {
    	message = mess;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public Object getMessage(){
        return message;
    }

    public Turtle getSenderMessage(){
        return sender;
    }
}
