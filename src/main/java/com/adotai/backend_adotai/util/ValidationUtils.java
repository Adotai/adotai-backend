package com.adotai.backend_adotai.util;

public class ValidationUtils {

    public static String formatStrNumber(String obj){
        return obj.replaceAll("\\D","");
    }

    public static boolean isValidCpf(String cpf){
        return cpf.replaceAll("\\D", "").length() == 11;
    }

    public static boolean isValidPhone(String number){
        return number.replaceAll("\\D","").length() == 11;
    }

    public static boolean isValidEmail(String email){
        return email != null && email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
    }

    public static boolean isValidCep(String cep){
        return cep.replaceAll("\\D","").length() == 8;
    }
}
