
public class AVLTree {

    private Node root;

    public AVLTree() {
        root = null;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    public int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    
    public void insert(int value) {
        System.out.println("Node a Insertar: " + value);
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            System.out.println("Nodo insertado: " + newNode.getKey() + " balance al insertar = " + getBalance(newNode));
            return newNode;
        }

        if (value < node.getKey()) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getKey()) {
            node.setRight(insertRec(node.getRight(), value));
        } else {
            return node; 
        }

        
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        
        int balance = getBalance(node);

        System.out.println("Node actual: " + node.getKey());
        System.out.println("\tAltura del nodo = " + node.getHeight());
        System.out.println("\tBalance del nodo = " + balance);

        

        
        if (balance > 1 && value < node.getLeft().getKey()) {
            System.out.println("Rotación Derecha (LL) en nodo " + node.getKey());
            return rightRotate(node);
        }

        
        if (balance < -1 && value > node.getRight().getKey()) {
            System.out.println("Rotación Izquierda (RR) en nodo " + node.getKey());
            return leftRotate(node);
        }

        
        if (balance > 1 && value > node.getLeft().getKey()) {
            System.out.println("Rotación Izquierda-Derecha (LR) en nodo " + node.getKey());
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        
        if (balance < -1 && value < node.getRight().getKey()) {
            System.out.println("Rotación Derecha-Izquierda (RL) en nodo " + node.getKey());
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    
    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        
        x.setRight(y);
        y.setLeft(T2);

        
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

        return x;
    }

    
    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

       
        y.setLeft(x);
        x.setRight(T2);

        
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));

        return y;
    }

    
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderRec(node.getRight());
        }
    }

    
    public void printTree() {
        printTreeRec(root, "", true);
    }

    private void printTreeRec(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getKey());
            printTreeRec(node.getRight(), prefix + (isTail ? "    " : "│   "), false);
            printTreeRec(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
