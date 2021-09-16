package search;

import java.util.ArrayList;
import java.util.HashMap;

public class SMain {
    public static void main(String[] args) throws Exception {

        ArrayList<HashMap<String, Object>> search = Search.main("Hyderabad");

        for (HashMap<String, Object> s : search) {
            System.out.println(s.toString());
        }
    }
}
