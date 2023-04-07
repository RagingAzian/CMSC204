import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class MorseCodeConverter {
    private static MorseCodeTree tree = new MorseCodeTree();
    public static String convertToEnglish(File file) throws FileNotFoundException{
        Scanner scan = new Scanner(file);
        return convertToEnglish(file);
    }
    public static String convertToEnglish(String s){
        s +=" ";
        String text = "";
        String code = "";
        for(String x: s.split("")){
            if (x.equals("/"))
                text += " ";
            else if(x.equals(" ")&&code.length()!=0){
                text+=tree.fetch(code);
            }
            else{
                code+=x;
            }
        }
        return text;
    }
    public static String printTree() {
		String s = "";
		ArrayList<String> list = tree.toArrayList();
		for (String x: list) {
			s += x + " ";
			System.out.println(s);
		}
		return s;
	}
}
