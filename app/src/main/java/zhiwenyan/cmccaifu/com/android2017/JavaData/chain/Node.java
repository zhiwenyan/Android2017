package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

/**
 * Description:链接节点
 * Data：6/11/2018-10:55 AM
 *
 * @author yanzhiwen
 */
public class Node {
    //数据域
    public int data;
    //节点域(指针域)
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print(data + ",");
    }
}
