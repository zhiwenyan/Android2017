package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

/**
 * Description:
 * Data：6/11/2018-10:59 AM
 *
 * @author yanzhiwen
 */
public class LinkList {
    private Node1 first;

    public LinkList() {
    }

    /**
     * 插入一个节点，在头节点后进行插入
     *
     * @param value
     */
    public void insertFirst(int value) {
        Node1 node = new Node1(value);
        node.next = first;
        first = node;

    }

    /**
     * 删除一个节点，在头节点后进行删除
     *
     * @return
     */
    public Node1 deleteFirst() {
        Node1 tmp = first.next;
        first = tmp.next;
        return tmp;
    }

    /**
     * 显示方法
     */
    public void display() {
        Node1 current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    /**
     * 查找方法
     *
     * @param value
     * @return
     */
    public Node1 find(int value) {
        Node1 current = first;
        while (current != null) {
            if (current.data == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * 删除方法：根据数据域来删除
     *
     * @return
     */
    public Node1 delete(int value) {
        Node1 current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
        }
        return current;
    }

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.insertFirst(66);
        linkList.insertFirst(23);
        linkList.insertFirst(88);
        linkList.display();

//        linkList.display();
        Node1 node = linkList.find(66);
        node.display();


    }
}
