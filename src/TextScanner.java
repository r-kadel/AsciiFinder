import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextScanner
{
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the file name to be scanned" + "\n");
        String fileName = in.nextLine();

        // Customize file path as needed for local

        Path p = Paths.get("/Users/ryan_kadel/WorkStuff/Pantheon/text-files/" + fileName + ".txt");
        Path newFIle = Paths.get("/Users/ryan_kadel/WorkStuff/Pantheon/text-files/" + fileName + "-scanResults.txt");

        // The New File Converts Incorrect Characters to "???" Control-f and replace!
        ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(p));
        CharBuffer cb = Charset.forName("windows-1252").decode(bb);
        bb = StandardCharsets.US_ASCII.encode(cb);
        Files.write(newFIle, bb.array());
    }

}
