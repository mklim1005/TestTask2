package objects;

import java.util.List;

public class WeatherResponse {

    private String address;
    public List<DayResponse> days;

    public String getAddress() {
        return address;
    }
}
