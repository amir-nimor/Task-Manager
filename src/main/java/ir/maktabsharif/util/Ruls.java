package ir.maktabsharif.util;

import ir.maktabsharif.exception.ValidationException;

public class Ruls {

    private Ruls(){}

    public static void check(boolean check,String message){
        if (check)throw new ValidationException(message);
    }
}
