import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        JSONArray object = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            object = (JSONArray) parser.parse("[{\"number\":\"375299110771\"},{\"number\":\"1234567\"}]");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object.stream().toArray());
    }
}
