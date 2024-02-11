import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ShortLister {


    public static void main(String[] args)
    {
        ArrayList<Object> shorts = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;

                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file read!");

                /*   Now process the lines in the arrayList
                     Split the line into the fields by using split with a comma
                     use trim to remove leading and trailing spaces
                     Numbers need to be converted back to numberic values. Here only
                     the last field year of birth yob is an int the rest are strings.*/

                String[] words;
                ShortWordFilter tiny = new ShortWordFilter();
                for(String l:lines)
                {
                    words = l.split(" "); // Split the record into individual words by splitting at spaces

                    for(String w : words)
                    {
                        if(tiny.accept(w))
                            shorts.add(w);
                    }


                }

            }
            else  // user closed the file dialog wihtout choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(collectAll(shorts));
    }

    public static Object collectAll(ArrayList m)
    {
        ShortWordFilter fil = new ShortWordFilter();
        ArrayList<Object> shortwords = new ArrayList<>();
        for (Object r: m)
        {
            if(fil.accept(r))
                shortwords.add(r);
        }
        return shortwords;
    }

}
