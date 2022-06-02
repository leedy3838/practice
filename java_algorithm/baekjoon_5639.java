import java.io.*;

public class Main{ 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tree tree = new Tree();

        while(true){
            String input = br.readLine();

            if(input == null)
                break;

            int num = Integer.parseInt(input);
            tree.createNode(new Node(num));
        }

        tree.postOrder();
    }
}

class Tree{
    Node root;

    void createNode(Node node){
        if(root == null)
            root = node;

        else
            createNode(root, node);
    }
    void createNode(Node node, Node newNode){
        if(node.data<newNode.data){
            if(node.right == null)
                node.right = newNode;
            else
                createNode(node.right, newNode);
        }
        else{
            if(node.left == null)
                node.left = newNode;
            else
                createNode(node.left, newNode);
        }
    }

    void postOrder(){
        postOrder(root);
    }
    void postOrder(Node node){
        if(node.left != null)
            postOrder(node.left);

        if(node.right != null)
            postOrder(node.right);

        System.out.println(node.data);
    }
}

class Node{
    Node left, right;
    int data;

    Node(int data){
        this.data = data;
    }
}
