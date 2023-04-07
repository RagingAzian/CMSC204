public class TreeNode <T>{
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild; 
    private T data;

    public TreeNode(){
    }
    
    public TreeNode(T data){
        this.data = data;
    }
    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.leftChild = node.leftChild;
        this.rightChild = node.rightChild;
    }

    public T getData(){
        return data;
    }

    public TreeNode<T> getLeftChild(){
        return leftChild;
    }
    
    public TreeNode<T> getRightChild(){
        return rightChild;
    }
    public void setLeftChild(TreeNode<T> leftChild){
        this.leftChild = leftChild;
    }
    
    public void setRightChild(TreeNode<T> rightChild){
        this.rightChild = rightChild;
    }
}
