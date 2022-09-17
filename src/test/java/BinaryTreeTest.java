import BinaryTree.BinaryTree;
import BinaryTree.Node;
import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void binaryTreeTest() {
//        Node head=null;
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.right = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.right.left.left = new Node(7);
        head.right.right.right = new Node(8);

        System.out.print("先序遍历（递归）：");
        BinaryTree.PreorderTraversal(head);
        System.out.println();
        System.out.print("先序遍历（循环）：");
        BinaryTree.PreorderTraversal_(head);
        System.out.println();
        System.out.print("中序遍历（递归）：");
        BinaryTree.MiddleTraversal(head);
        System.out.println();
        System.out.print("中序遍历（循环）：");
        BinaryTree.MiddleTraversal_(head);
        System.out.println();
        System.out.print("中序遍历（递归）：");
        BinaryTree.PostorderTraversal(head);
        System.out.println();
        System.out.print("后序遍历（循环）：");
        BinaryTree.PostorderTraversal_(head);
        System.out.println();
        System.out.print("层次遍历：");
        BinaryTree.LevelTraversal(head);
        System.out.println();
        System.out.println("树的宽度："+BinaryTree.getWidth(head));
        System.out.println("树的深度："+BinaryTree.getDepth(head));
    }
}
