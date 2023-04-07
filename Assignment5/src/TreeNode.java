public class TreeNode <T>{
    protected TreeNode<T> leftChild;
    protected TreeNode<T> rightChild; 
    protected T data;

    public TreeNode(){
    }
    
    public TreeNode(T data){
        this.data = data;
        leftChild = null;
        rightChild = null;
    }
    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.leftChild = node.leftChild;
        this.rightChild = node.rightChild;
    }

    public T getData(){
        return data;
    }
}
