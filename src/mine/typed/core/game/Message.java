package mine.typed.core.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Message {
    
    public Object des, src, data;
    public String type;
    
    public Message(Object Des, Object Src, String Type, Object Data){
	des = Des;
	src = Src;
	type = Type;
	data = Data;
    }
    
    public Message(String Des, String Src, String Type, String Data){
	des = Des;
	src = Src;
	type = Type;
	data = Data;
    }

    public Object getDes() {
        return des;
    }

    public Object getSrc() {
        return src;
    }

    public Object getData() {
        return data;
    }

    public String getType() {
        return type;
    }
    
    public  static Message getMessageFromJSON(String json){
	JSONParser par = new JSONParser();
	Message msg = new Message("null", "null", "null", "wrong data");
	Object tmp = null;
	try {
	    tmp = par.parse(json);
	} catch (ParseException e) {
	    e.printStackTrace();
	    return msg;
	}
	
	JSONObject obj = (JSONObject) tmp;
	msg.des = (String)obj.get("Des");
	msg.src = (String)obj.get("Src");
	msg.type = (String)obj.get("Type");
	msg.data = (String)obj.get("Data");
	
	return msg;
    }
    
    @SuppressWarnings("unchecked")
    public String getJSONString(){
	JSONObject data = new JSONObject();
	
	try {
	    data.put("Des",(String)des);
	    data.put("Src",(String)src);
	    data.put("Type",type);
	    data.put("Data",(String)this.data);
	    
	} catch (Exception e) {e.printStackTrace();}
	return data.toString();
    }
    
    
}
