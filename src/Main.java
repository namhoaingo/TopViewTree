import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
// Tree Creations

//    2
//            2
//            1 2 L 1 3 R
//5
//        10 20 L 10 30 R 20 40 L 20 60 R 30 90 L
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int caseCount = scanner.nextInt();
       scanner.nextLine();
        List<Tree> trees = new ArrayList<>();
       for(int i= 0; i < caseCount; i++) {
           int childNodeCount = scanner.nextInt();
           System.out.println();
           System.out.println("ChildNodeCount" + childNodeCount);
           //scanner.nextLine();
           Tree tree = new Tree();
           for (int j = 0; j < childNodeCount; j++){
               int parentData = scanner.nextInt();
               int childData = scanner.nextInt();
               char direction = scanner.next().charAt(0);
//               System.out.println("ParentData" + parentData);
//               System.out.println("ChildData" + childData);
//               System.out.println("Direction" + direction);
               switch (direction)
               {
                   case 'L':
                       tree.AddNode(parentData, Direction.Left, childData);
                       break;
                   case 'R':
                       tree.AddNode(parentData, Direction.Right, childData);
               }
           }
           trees.add(tree);
       }
       scanner.close();
       for(Tree tree: trees){
           tree.printTree();
       }
    }
}

class Tree{
    private TreeNode root = null;
    public Tree(){}

    public TreeNode getRoot(){
        return root;
    }

    public void AddNode(int underNode, Direction direction, int data ){
        // Create a new Tree Node
        //System.out.println("underNode " + underNode);
        //System.out.println("data " + data);
        if(root == null){
            TreeNode tempRootNode = new TreeNode();
            tempRootNode.setData(underNode);
            root = tempRootNode;

            TreeNode tempAddingNode = new TreeNode();
            tempAddingNode.setData(data);
            addNodeOnDirection(root, tempAddingNode, direction);
        }
        else
        {
            TreeNode newNode = new TreeNode();
            newNode.setData(data);
            // Traver the tree to find the correct spot to insert data in
            TreeNode parentNode = findNode(root, underNode);
            System.out.println("Parent Node" + parentNode);
            System.out.println("Child Node" + newNode.getData());
            addNodeOnDirection(parentNode, newNode, direction);
        }
    }

    public void addNodeOnDirection (TreeNode parentNode, TreeNode newNode, Direction direction){
        if (direction == Direction.Left)
        {
            parentNode.setLeftNode(newNode);
        }else{
            parentNode.setRightNode(newNode);
        }
    }

    public TreeNode findNode(TreeNode node, int data){
        TreeNode result = null;
        if(node.getData() == data){
            result = node;
        }
        else{
            if(node.getLeftNode() != null)
            {
                result = findNode(node.getLeftNode(), data);
            }

            if(result == null && node.getRightNode() != null){
                result =  findNode(node.getRightNode(), data);
            }
        }
        return result;
    }

    public void printTree(){
        printNode(root);
    }

    public void printNode(TreeNode node){
        if(node.getRightNode() != null){
            printNode(node.getRightNode());
        }
        if(node.getLeftNode() != null){
            printNode(node.getLeftNode());
        }
        System.out.print ("Node Data" + node.getData());
    }
}

class TreeNode{
    private int data;
    private TreeNode leftNode;
    private TreeNode rightNode;
    public TreeNode(){ }

    // Encapsulation
    public void setData(int _data){
        data = _data;
    }

    public int getData(){
        return data;
    }

    public void setLeftNode(TreeNode node){
        leftNode= node;
    }

    public TreeNode getLeftNode(){
        return leftNode;
    }

    public void setRightNode(TreeNode node){
        rightNode = node;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }
}

enum Direction{
    Left, Right
}
