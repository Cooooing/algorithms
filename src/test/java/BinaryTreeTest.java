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

        BinaryTree.PreorderTraversal(head);
        System.out.println();
        BinaryTree.MiddleTraversal(head);
        System.out.println();
        BinaryTree.PostorderTraversal(head);
        System.out.println();
        BinaryTree.LevelTraversal(head);
        System.out.println();
        System.out.println("树的宽度："+BinaryTree.getWidth(head));
        System.out.println("树的深度："+BinaryTree.getDepth(head));
    }
}
