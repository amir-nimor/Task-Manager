package ir.maktabsharif.service.help;

public class Email implements Notofy{


    @Override
    public void log(String message) {
        System.out.println("Email send log = "+message);
    }
}
