package lv.home.weather.model.weather;

public class WindData {
    private double speed;
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "WindData{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
