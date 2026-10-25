package ir.maktabsharif.service.help;

public class LogService {

    private Notofy notofy;


    public LogService(Notofy notofy){
        this.notofy = notofy;
    }


    public void log(String message){
        notofy.log(message);
    }
}
