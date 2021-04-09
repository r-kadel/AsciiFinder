import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class AsciiFinder
{
    //
    public static void main(String[] args) throws IOException {
        //take in filename from user
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the file name to be scanned" + "\n");
        String fileName = in.nextLine();

        // Customize file path as needed for local
        File file = new File("/Users/ryan_kadel/WorkStuff/Pantheon/text-files/" + fileName + ".txt");

        String uncleanContent = readFileIntoString(file);

        String cleanContent = cleanTextContent(uncleanContent);

        //System.out.println(cleanContent);

        FileWriter writer = new FileWriter("/Users/ryan_kadel/WorkStuff/Pantheon/text-files/" + fileName + "-scanResults.txt");
        writer.write(cleanContent);
        writer.close();
    }

    private static String readFileIntoString(File file)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(file.toURI())))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            System.out.println("Error reading " + file.getAbsolutePath());
        }
        return contentBuilder.toString();
    }

    private static String cleanTextContent(String text)
    {
        // locate off all non-ASCII characters
       // text = text.replaceAll("[^\\x00-\\x7F]", "***");

        // locates all the ASCII control characters
       // text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "***");

        // locates non-printable characters from Unicode
        // Leaving in code for now but seems broken and picks up weird spaces and stuff
         text = text.replaceAll("\\p{C}", "***");

        return text.trim();
    }
}
