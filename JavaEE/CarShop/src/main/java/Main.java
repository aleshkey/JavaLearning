import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println( String.valueOf(ZonedDateTime.now()).split("T")[0] );
    }
}
