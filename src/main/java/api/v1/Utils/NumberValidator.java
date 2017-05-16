package api.v1.Utils;

/**
 * Created by Vlad Stefan on 5/14/2017.
 */
public class NumberValidator implements Validator
{
    /**
     * Checks if a given string is numeric
     * @param toValid - object to be tested
     * @return a boolean, validation result
     */
    public boolean isValid(Object toValid)
    {
        try
        {
            String number = (String) toValid;
            double nr = Double.parseDouble(number);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
