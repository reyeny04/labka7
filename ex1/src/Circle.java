import java.io.Serializable;

class Circle extends GeometricObject implements Serializable {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}
