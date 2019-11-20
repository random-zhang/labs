package net.Bridge;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());
        Shape yellowRectangle=new Rectangle(22,22,new yellowRectangle());
        redCircle.draw();
        greenCircle.draw();
        yellowRectangle.draw();
    }
}