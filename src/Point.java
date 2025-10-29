public class Point {
    public double x;
    public double y;
    public double heading;

    public double moveSpeed;
    public double turnSpeed;

    public String label;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        heading = 0;

        label = "";

        moveSpeed = 0.75;
        turnSpeed = 0.75;
    }

    public Point(double x, double y, double h, String label, double moveSpeed, double turnSpeed) {
        this.x = x;
        this.y = y;
        this.heading = h;
        this.label = label;
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
    }

    public Point(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;

        label = "";

        moveSpeed = 0.75;
        turnSpeed = 0.75;
    }

    public Point(Point copy) {
        x = copy.x;
        y = copy.y;
        heading = copy.heading;
        label = copy.label;
        moveSpeed = copy.moveSpeed;
        turnSpeed = copy.turnSpeed;
    }

    public Point toField() {
        return new Point(((6.0/25.0) * x - 72), ((6.0/25.0) * y - 72));
    }

    public Point toPixels() {
        return new Point(((25.0/6.0) * x + 300), ((25.0/6.0) * y + 300));
    }

    public void truncate() {
        x = Math.round(x * 1000.0) / 1000.0;
        y = Math.round(y * 1000.0) / 1000.0;
    }

    public static double toField(double input) {
        return ((6.0/25.0) * input - 72);
    }

    public static int toPixels(double input) {
        return (int)((25.0/6.0) * input + 300);
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2) + Math.pow(heading - p.heading, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + heading + ")";
    }
}
