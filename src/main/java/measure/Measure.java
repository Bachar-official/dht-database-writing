package measure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measure {
    private Integer id;
    private LocalDateTime date;
    private double temperature;
    private double humidity;

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

    public Measure(double temp, double humi) {
        this.date = LocalDateTime.now();
        this.temperature = temp;
        this.humidity = humi;
    }

    public Measure(Integer id, String date, double temp, double humi) {
        this.id = id;
        this.date = LocalDateTime.parse(date);
        this.temperature = temp;
        this.humidity = humi;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("Дата: %s;\nВремя: %s;\nТемпература: %.1f°C;\nВлажность: %.1f%%.", date.format(dateFormat),
                date.format(timeFormat), temperature, humidity);
    }
}