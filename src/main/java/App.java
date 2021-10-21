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

        System.out.println("Database initialization...");
        try {
            handler = DbHandler.getInstance();
            System.out.println("Success. Sensor initialization...");
            try {
                BME280 bme = new BME280();
                System.out.println("Success. Measuring every 5 minutes.");
                while (true) {
                    Measure measure = new Measure(0.0, 0.0, 0.0);
                    try {                        
                        measure = bme.getMeasure();
                    } catch (Exception e)
                    {
                        System.out.println("Something went wrong!");
                    }
                    System.out.println("Measure:\n" + measure.toString());
                    if (measure.getHumidity() != 0.0) {
                        handler.addMeasure(measure);
                    }                    
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
