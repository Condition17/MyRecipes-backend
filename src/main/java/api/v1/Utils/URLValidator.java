package api.v1.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad Stefan on 5/14/2017.
 */


/**
 * This Class has only one method that
 * checks if a given string represents an URL
 */
public class URLValidator implements Validator {

    private String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

    /**
     * This method checks if the given string represents an URL
     * @URL the string to be evaluated
     * @return a boolean, the validation result
     */
    public boolean isValid(Object toValidate){

        String URL;
        try {
            URL = (String) toValidate;
        }
        catch (Exception e)
        {
            return false;
        }
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(URL);

        return matcher.find();
    }

}
