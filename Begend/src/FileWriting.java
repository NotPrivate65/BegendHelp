import java.io.*;

public class FileWriting {

    public void appendToFile(String file, String toAppend){

        try{
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file,true));
            buffWriter.write(toAppend);

            buffWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
