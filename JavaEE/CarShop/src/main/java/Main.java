import com.shop.carshop.DAO.CarDAO;
import com.shop.carshop.models.Car;
import com.shop.carshop.models.Condition;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.setYearOfRelease(2019);
        car.setCondition(Condition.newCondition);
        car.setMark("Mercedes");
        car.setModel("AMG");
        car.setOwnerID(1);
        new CarDAO().addCar(car);
    }
}
