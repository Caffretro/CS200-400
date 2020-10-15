
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class jsonParserTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object fileObj = parser.parse(new FileReader("valid.json"));
        JSONObject jsonObject = (JSONObject) fileObj;
        JSONArray courses = (JSONArray) jsonObject.get("courses");
        System.out.println(courses);
        for (int i = 0; i < courses.size(); i++) {
            JSONObject object2 = (JSONObject) courses.get(i);
            String name = (String) object2.get("name");
            System.out.println(name);
            ArrayList preq = (ArrayList) object2.get("prerequisites");
            System.out.println(preq);
        }
        
    }

}
