package ir.maktabsharif.service.help;

public class Sms implements Notofy{
    @Override
    public void log(String message) {
        System.out.println("SMS send log = "+message);
    }
}
