package zhiwenyan.cmccaifu.com.android2017.banner.适配器模式;

/**
 * Created by zhiwenyan on 6/1/17.
 */
interface ICircle{
    void drawCircle();
}
interface ISquare{
    void drawSquare();
}
//圆形
class Circle implements ICircle{
    public void drawCircle(){
        System.out.println("Draw circle");
    }
}
//方形
class Square implements ISquare{
    public void drawSquare(){
        System.out.println("Draw square");
    }
}
//既可以画圆形，又可以画方形，适配器
public class HybridShape implements ICircle, ISquare {
    private ISquare square;
    private ICircle circle;

    public HybridShape(Square square) {
        this.square = square;
    }

    public HybridShape(Circle circle) {
        this.circle = circle;
    }

    public void drawSquare() {
        square.drawSquare();
    }

    public void drawCircle() {
        circle.drawCircle();
    }
}
