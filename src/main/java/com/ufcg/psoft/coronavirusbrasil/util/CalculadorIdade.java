package com.ufcg.psoft.coronavirusbrasil.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalculadorIdade {

    public static int calculaIdade(Date nascimento) {
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(nascimento);
        Calendar today = Calendar.getInstance();

        return today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
    }
}
