import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree t = new MorseCodeTree();

	public static String convertToEnglish(File inputFile) throws FileNotFoundException {
		try (Scanner scan = new Scanner(inputFile)) {
            return convertToEnglish(scan.nextLine());
        }
	}

	public static String convertToEnglish(String s) {
		s += " ";
		String text = "";
		String code = "";
		for(String index : s.split("")) {
			if(index.equals("/")) {
				text += " ";
			}
			else if(index.equals(" ")) {
				if(code.length()!=0) {
					text += t.fetch(code);
					code = "";
				}
			} else {
				code += index;
			}
		}
		return text;
	}

	public static String printTree() {
		String text = "";
		ArrayList<String> arr = t.toArrayList();
		for (String s: arr) {
			text += s + " ";
		}
        text=text.substring(0,text.length()-1);
        System.out.println(text);
		return text;
	}

}