package zhiwenyan.cmccaifu.com.android2017.JavaData.tree;

/**
 * Description:
 * Data：2/15/2019-10:55 AM
 *
 * @author yanzhiwen
 */
public class TreeNode<T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }

    public void preOrderTraverse(TreeNode<T> pNode) {
        if (pNode == null) {
            return;
        }
        System.out.println(pNode.data);
        preOrderTraverse(pNode.left);
        preOrderTraverse(pNode.right);
    }

    public static void main(String[] args) {
        TreeNode<Character> A = new TreeNode<>('A');
        TreeNode<Character> B = new TreeNode<>('B');
        TreeNode<Character> C = new TreeNode<>('C');
        TreeNode<Character> D = new TreeNode<>('D');
        TreeNode<Character> E = new TreeNode<>('E');
        TreeNode<Character> F = new TreeNode<>('F');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.right = F;

        A.preOrderTraverse(A);


    }
}
