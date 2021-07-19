import DHT.DHT22;
import DHT.DHTData;
import DHT.DHTxx;
import com.pi4j.io.gpio.RaspiPin;
import measure.Measure;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        Integer SLEEP_PERIOD = 300000;
        DbHandler handler = null;

        System.out.println("Инициализация БД...");
        try {
            handler = DbHandler.getInstance();
            System.out.println("Успешно. Инициализация сенсора...");
            try {
                DHTxx sensor = new DHT22(RaspiPin.GPIO_07);
                sensor.init();
                System.out.println("Успешно. Измеряемс каждые 5 минут.");
                while (true) {
                    DHTData data = sensor.getData();
                    Measure measure = data.toMeasure();
                    System.out.println("Измерение:\n" + measure.toString());
                    handler.addMeasure(measure);
                    Thread.sleep(SLEEP_PERIOD);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
