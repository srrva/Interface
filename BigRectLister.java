import java.awt.*;
import java.util.ArrayList;

public class BigRectLister
{

    public static void main(String[] args)
    {
        ArrayList<Object> rects = new ArrayList<>();
        rects.add(new Rectangle(1,1));
        rects.add(new Rectangle(2,1));
        rects.add(new Rectangle(3,1));
        rects.add(new Rectangle(1,4));
        rects.add(new Rectangle(2,3));
        rects.add(new Rectangle(8,25));
        rects.add(new Rectangle(10,30));
        rects.add(new Rectangle(2,100));
        rects.add(new Rectangle(5,6));
        rects.add(new Rectangle(38,12));
        System.out.println(collectAll(rects));
    }

    public static Object collectAll(ArrayList m)
    {
        BigRectangleFilter fil = new BigRectangleFilter();
        ArrayList<Object> bigrects = new ArrayList<>();
        for (Object r: m)
        {
            if(fil.accept(r))
                bigrects.add(r);
        }
        return bigrects;
    }


}
