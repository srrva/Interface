public class ShortWordFilter implements Filter {
    @Override
    public boolean accept(Object x)
    {
        String word = (String) x;
        if (word.length() < 5)
            return true;
        else
            return false;
    }
}
