


import java.util.HashMap;

public class MathHashMap extends HashMap<String, Handler> {

	private static final long serialVersionUID = 4493187623849475102L;
	
	public MathHashMap(){
        this.put("newFruitProblem", new NewProblemFruitHandler());
    }



}
