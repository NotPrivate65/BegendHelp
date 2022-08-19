import java.io.*;

public class FileReadingSystem {

    int a = 1;
    private String [][]table_txt = new String[4000000][4];

    public void readFileTxt(String file){


        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;

            int line_count = 0;

            while (reader.readLine() != null) {
                a++;
            }

            while ((line = reader.readLine()) != null) {

                String[] cache = line.split(";");

                for (int i = 0; i < cache.length; i++) {
                    table_txt[line_count][i] = cache[i];
                }
                line_count++;
            }
            reader.close();


        } catch (
                FileNotFoundException ex) {

            throw new RuntimeException(ex);
        } catch (IOException exep) {
            throw new RuntimeException(exep);
        }

    }

    public String getTableTxt(int i, int j){
        return table_txt[i][j];
    }
    public int getTableTxtLenght(){
        return table_txt.length;
    }
    public int getTableTxtPlaceLenght(int i){
        return table_txt[i].length;
    }

}
