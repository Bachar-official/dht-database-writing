package measure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measure {
    private Integer id;
    private LocalDateTime date;
    private double temperature;
    private double humidity;
    private double pressure;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public Measure(double temp, double humi, double pressure) {
        this.date = LocalDateTime.now();
        this.temperature = temp;
        this.humidity = humi;
        this.pressure = pressure;
    }

    public Measure(Integer id, String date, double temp, double humi, double press) {
        this.id = id;
        this.date = LocalDateTime.parse(date);
        this.temperature = temp;
        this.humidity = humi;
        this.pressure = press;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("Date: %s;\nTime: %s;\nTemperature: %.1f°C;\nHumidity: %.1f%%;\nPressure: %.1fmm", date.format(dateFormat),
                date.format(timeFormat), temperature, humidity, pressure);
    }
}
