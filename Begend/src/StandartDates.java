import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class StandartDates{

    public String getDDMMYYYY(){

        Date date = new Date();
        String date_All = date.toString();
        String [] date_buff = date_All.split(" ");
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();


        String norm = date_buff[2] +"."+ month +"."+ date_buff[5];

        return norm;
    }

    public String getHHMMSS(){

        Date date = new Date();
        String date_All = date.toString();
        String [] date_buff = date_All.split(" ");
        String time = date_buff[3];


        return time;
    }

    public String getCW(){
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int week = c.get(Calendar.WEEK_OF_YEAR);
        String cw = String.valueOf(week);

        return cw;
    }
    public String getMMYYYY(){
        String year = "";
        Date d = new Date();
        String buffer = d.toString();
        String [] bufferSplit = buffer.split(" ");
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        year = month +"."+ bufferSplit[5];


        return year;
    }


    public boolean equalsCW(String date1, String date2){

        boolean output = false;

        SimpleDateFormat spdf = new SimpleDateFormat("dd.MM.yyyy");

        Date d1;
        Date d2;

        try {
            d1 = spdf.parse(date1);
            d2 = spdf.parse(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);
        c2.setTime(d2);

        int cw1 = c1.get(Calendar.WEEK_OF_YEAR);
        int cw2 = c1.get(Calendar.WEEK_OF_YEAR);

        if(cw1 == cw2){
           output = true;
        }

        return output;
    }

}
