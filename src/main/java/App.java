/*import DHT.DHT22;
import DHT.DHTData;
import DHT.DHTxx;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CBus;*/

import BME280.BME280;
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
                BME280 bme = new BME280();
                System.out.println("Успешно. Измеряем каждые 5 минут.");
                while (true) {
                    Measure measure = bme.getMeasure();
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
