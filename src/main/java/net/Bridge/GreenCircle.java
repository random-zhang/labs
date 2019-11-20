package net.Bridge;

public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }

    @Override
    public void drawRectangle(int height, int weight) {

    }
}
