package zhiwenyan.cmccaifu.com.android2017.JavaData.chain;

/**
 * Description:
 * Data：6/11/2018-10:59 AM
 *
 * @author yanzhiwen
 */
public class LinkList {
    private Node first;

    public LinkList() {
    }

    /**
     * 插入一个节点，在头节点后进行插入
     *
     * @param value
     */
    public void insertFirst(int value) {
        Node node = new Node(value);
        node.next = first;
        first = node;

    }

    /**
     * 删除一个节点，在头节点后进行删除
     *
     * @return
     */
    public Node deleteFirst() {
        Node tmp = first.next;
        first = tmp.next;
        return tmp;
    }

    /**
     * 显示方法
     */
    public void display() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;

        }
        System.out.println();
    }

    /**
     * 查找方法
     *
     * @param value
     * @return
     */
    public Node find(int value) {
        Node current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * 删除方法：根据数据域来删除
     *
     * @return
     */
    public Node delete(int value) {
        Node current = first;
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
//        Node node = linkList.find(66);
//        node.display();
    }
}
