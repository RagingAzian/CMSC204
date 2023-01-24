import java.util.ArrayList;
public class PasswordCheckerUtility {
    public static void comparePasswords(String password, String passwordConfirm){
        if(!password.equals(passwordConfirm))
            throw new UnmatchedException("Password is not the same");
    }

    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
        return(password.equals(passwordConfirm));
    }

    public static boolean isValidLength(String password){
        if(password.length()>=6)
            return true;
        else
            throw new LengthException("The password must be at least 6 characters long");
    }

    public static boolean hasUpperAlpha(String password){
        for(int x = 0;x<password.length();x++){
            if(Character.isUpperCase(password.charAt(x)))
                return true;
        }
        throw new NoUpperAlphaException("Must contain an uppercase alpha character")
    }

    public static boolean hasLowerAlpha(String password){
        for(int x = 0;x<password.length();x++){
            if(Character.isLowerCase(password.charAt(x)))
                return true;
        }
        throw new NoLowerAlphaException("Must contain an lowercase alpha character");
    }

    public static boolean hasDigit(String password){
        for(int x = 0;x<password.length();x++){
            if(Character.isDigit(password.charAt(x)))
                return true;
        }
        throw new NoDigitException("password must contain a numeric character");
    }

    public static boolean hasSpecialChar(String password){
        for(int x =0;x<password.length();x++){
            if(!Character.isLetter(password.charAt(x))&&!Character.isDigit(password.charAt(x)))
                return true;
        }
        throw new NoSpecialCharacterException("Password must contain a special character");
    }
}
