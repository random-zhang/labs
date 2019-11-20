package net.Bridge;

public  class Rectangle extends Shape {
    int height,width;
    protected Rectangle(int x, int y, DrawAPI drawAPI) {
        super(drawAPI);
        this.height=x;
        this.width=y;
    }
    public   void draw(){
        drawAPI.drawRectangle(height,width);
    };
}
