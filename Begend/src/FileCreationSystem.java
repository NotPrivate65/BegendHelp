import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileCreationSystem {

    private String dayPath = "";
    private String cwPath = "";

    public void createFileSystem(){

        String file = System.getProperty("user.dir");

        String [] file_Buffer = file.split(Pattern.quote(File.separator));

        String file_System_Buffer = "";

        for(int i = 0; i < 2; i++){
            file_System_Buffer += file_Buffer[i] + " ";
        }

        file_System_Buffer += file_Buffer[2];

        String file_System = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend";

        File file_Whole = new File(file_System);
        file_Whole.mkdir();

        String toWriteFile = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend\\Day.bgd";
        File file1 = new File(toWriteFile);

        dayPath = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend\\Day.bgd";;

        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private String userPathDay = "";
    private String userPathCW = "";

    public void nameFileCreation(String name){

        String file = System.getProperty("user.dir");

        String [] file_Buffer = file.split(Pattern.quote(File.separator));

        String file_System_Buffer = "";

        for(int i = 0; i < 2; i++){
            file_System_Buffer += file_Buffer[i] + " ";
        }
        file_System_Buffer += file_Buffer[2];

        //DirCreator
        String file_System = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend";

        File file_Whole = new File(file_System);
        file_Whole.mkdir();

        String personal_File = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend\\"+name;
        File userDir = new File(personal_File);
        userDir.mkdir();

        //FileCreator

        String toWriteFileDay = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend\\"+name+"\\Day.bgd";
        File txtFilesCreatorDay = new File(toWriteFileDay);
        String toWriteFileCW = file_System_Buffer.replace(" ", "\\") + "\\AppData\\Roaming\\Begend\\"+name+"\\CW.bgd";
        File txtFilesCreatorCW = new File(toWriteFileCW);

        userPathDay = toWriteFileDay;
        userPathCW = toWriteFileCW;

        try {
            txtFilesCreatorDay.createNewFile();
            txtFilesCreatorCW.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String getUserPathDay(){
        return userPathDay;
    }




    public String getFilePathDay(){
        return dayPath;
    }
    public String getFilePathCW(){
        return cwPath;
    }



    /*
    public String getFilePathYear(){
    }
     */

}
