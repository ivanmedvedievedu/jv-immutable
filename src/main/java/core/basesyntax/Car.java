package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Car {
    private final int year;
    private final String color;
    private final List<Wheel> wheels;
    private final Engine engine;

    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;

        Objects.requireNonNull(wheels, "The wheel lis cannot be null");
        List<Wheel> newWheels = new ArrayList<>(wheels.size());
        for (Wheel wheel : wheels) {
            newWheels.add(new Wheel(wheel.getRadius()));
        }
        this.wheels = newWheels;

        if (engine == null) {
            this.engine = null;
        } else {
            this.engine = new Engine(engine.getHorsePower(), engine.getManufacturer());
        }
    }

    public Car changeEngine(Engine engine) {
        return new Car(this.year,
                this.color,
                this.wheels,
                new Engine(engine.getHorsePower(),
                        engine.getManufacturer()));
    }

    public Car changeColor(String newColor) {
        return new Car(this.year, newColor, this.wheels, this.engine);
    }

    public Car addWheel(Wheel newWheel) {
        List<Wheel> newWheels = new ArrayList<>(wheels.size() + 1);
        for (Wheel wheel : wheels) {
            if (wheel == null) {
                continue;
            }
            newWheels.add(new Wheel(wheel.getRadius()));
        }
        newWheels.add(newWheel);
        return new Car(this.year, this.color, newWheels, this.engine);
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public List<Wheel> getWheels() {
        if (wheels == null) {
            return null;
        } else {
            List<Wheel> newWheels = new ArrayList<>(wheels.size());
            for (Wheel wheel : wheels) {
                newWheels.add(new Wheel(wheel.getRadius()));
            }
            return newWheels;
        }
    }

    public Engine getEngine() {
        if (engine == null) {
            return null;
        } else {
            return new Engine(engine.getHorsePower(), engine.getManufacturer());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Car car = (Car) object;
        return year == car.year
                && Objects.equals(color, car.color)
                && Objects.equals(wheels, car.wheels)
                && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 17 * result + (color != null ? color.hashCode() : 0);
        result = 17 * result + wheels.hashCode();
        result = 17 * result + (engine != null ? engine.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Car{"
            + "year=" + year
            + ", color='" + color + '\''
            + ", wheels=" + wheels
            + ", engine=" + engine
            + '}';
    }
}
