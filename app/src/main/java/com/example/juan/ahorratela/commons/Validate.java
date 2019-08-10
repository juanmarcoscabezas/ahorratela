package com.example.juan.ahorratela.commons;

import android.icu.text.StringPrepParseException;
import android.widget.EditText;

/**
 * clase diseñada para validar
 * Created by Matthew Seidel on 05/10/18.
 */

public class Validate {
    final String VERSION="1.0.1";

    /**
     * valida que lo textos estén vacíos
     *
     * @param texto cadena a validar
     * @return true si no está vacío false si está vacío
     * @deprecated
     */
    public static boolean validarTexto(String texto) {
        boolean bool = true;
        if (texto.isEmpty()) {
            bool = false;
        }
        if (texto == "") {
            bool = false;
        }
        if (texto == null) {
            bool = false;
        }
        return bool;
    }

    /**
     * valida que el edit text no esté vacío
     *
     * @param texto edit text a validar
     * @return texto ya validado
     * @throws Exception si el editText está vacío
     */
    public static String validarTexto(EditText texto) throws Exception {
        String text = texto.getText().toString();
        while(text.indexOf("  ")!=-1){
            text=text.replace("  ", " ");
        }
        if (text.length() < 3) {
            texto.requestFocus();
            throw new Exception("los campos deben llevar más de 3 digitos");
        }

        if (text.isEmpty() || text.equals(" ")) {
            texto.requestFocus();
            throw new Exception("los campos deben llevar un valor");
        }
        return text;
    }

    public static int validarNumber(EditText texto) throws Exception {
        String text = texto.getText().toString();
        if (text.isEmpty() || text.equals(" ")) {
            texto.requestFocus();
            throw new Exception("los campos deben llevar un valor");
        }
        return Integer.parseInt(text);
    }
}
