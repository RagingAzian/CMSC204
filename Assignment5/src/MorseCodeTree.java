import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
    TreeNode<String> root = new TreeNode<String>();
    
    public MorseCodeTree(){
        buildTree();
    }
    public TreeNode<String> getRoot(){
        return root;
    }

    public void setRoot(TreeNode<String> newNode){
        root = new TreeNode<String>(newNode);
    }

    public LinkedConverterTreeInterface<String> insert(String code, String result){
        addNode(root, code, result);
        return this;
    }

    public void addNode(TreeNode<String> root, String code, String letter){
        if(code.length()==1 && code.equals(".")){
            root.setLeftChild(new TreeNode<String>(letter));
        }
        else if(code.length()==1 && code.equals("-")){
            root.setRightChild(new TreeNode<String>(letter));
        }
        else if(code.length()!=1 && code.charAt(0)=='-')
            addNode(root.getRightChild(), code.substring(1), letter);
        else if(code.length()!=1 && code.charAt(0)=='.')
            addNode(root.getLeftChild(), code.substring(1),letter);
    }
    public String fetch(String code){
        return fetchNode(root, code);
    }
    public String fetchNode(TreeNode<String> root, String code) {
        char[] directions = code.toCharArray();
        TreeNode<String> currentNode = root;
    
        for (char direction : directions) {
            if (direction == '.') {
                currentNode = currentNode.getLeftChild();
            } else if (direction == '-') {
                currentNode = currentNode.getRightChild();
            } else {
                return null;
            }
        }
        return currentNode.getData();
    }

    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
    public void buildTree(){
        setRoot(new TreeNode<String>(""));

		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");

    }
    public ArrayList<String> toArrayList(){
        ArrayList<String> list = new ArrayList<String>();
        LNRoutputTraversal(root, list);
        return list;
    }

    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		LNRoutputTraversal(root.getLeftChild(), list);
		list.add(root.getData());
		LNRoutputTraversal(root.getRightChild(), list);
	}
}
