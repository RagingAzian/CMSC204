import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	protected TreeNode<String> root = new TreeNode<String>();

	public MorseCodeTree() {
		// call buildtree
		buildTree();
	}

	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}

	@Override
	public void insert(String code, String result) {
		addNode(root, code, result);
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
        for (int i = 0; i < code.length(); i++) {
            char direction = code.charAt(i);
            if (direction == '.') {
                if (root.leftChild == null) {
                    root.leftChild = new TreeNode<String>();
                }
                root = root.leftChild;
            } else if (direction == '-') {
                if (root.rightChild == null) {
                    root.rightChild = new TreeNode<String>();
                }
                root = root.rightChild;
            }
        }
        root.data = letter;
    }

	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		for (int i = 0; i < code.length(); i++) {
            char direction = code.charAt(i);
            if (direction == '.') {
                root = root.leftChild;
            } else if (direction == '-') {
                root = root.rightChild;
            }
            if (root == null) {
                return null;
            }
        }
        return root.getData();
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
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

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> o = new ArrayList<String>();
		LNRoutputTraversal(root, o);
		return o;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		LNRoutputTraversal(root.leftChild, list);
		list.add(root.getData());
		LNRoutputTraversal(root.rightChild, list);
	}

}