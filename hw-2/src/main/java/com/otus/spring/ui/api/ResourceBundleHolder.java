package com.otus.spring.ui.api;

import java.util.Locale;
import java.util.ResourceBundle;

public interface ResourceBundleHolder {

    ResourceBundle getBundle();

    void setLocale(Locale locale);

}
