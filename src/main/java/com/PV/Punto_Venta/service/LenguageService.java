package com.PV.Punto_Venta.service;

import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class LenguageService {

    private Locale currentLocale = new Locale("es");

    public ResourceBundle getBundle() {
        // RUTA CORRECTA: Apunta a la carpeta /i18n/messages
        return ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public void setLanguage(String languageCode) {
        this.currentLocale = new Locale(languageCode);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public String getMessage(String key) {
        return getBundle().getString(key);
    }
}