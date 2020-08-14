package org.pharmacymanagement;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class test {
    public static void main(String args[]){

        Locale locale = Locale.ENGLISH;

        NumberFormat nf = NumberFormat.getNumberInstance(locale);
// for trailing zeros:
        nf.setMinimumFractionDigits(2);
// round to 2 digits:
        nf.setMaximumFractionDigits(2);

        System.out.println(nf.format(.99));
        System.out.println(nf.format(123.567));
        System.out.println(Double.parseDouble(nf.format(123.10)));
    }
}
