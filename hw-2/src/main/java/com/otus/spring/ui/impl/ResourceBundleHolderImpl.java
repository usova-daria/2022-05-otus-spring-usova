package com.otus.spring.ui.impl;

import com.otus.spring.ui.api.ResourceBundleHolder;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleHolderImpl implements ResourceBundleHolder {

    private final String resourceBundleName;
    private Locale locale = Locale.getDefault();

    public ResourceBundleHolderImpl(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    @Override
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(resourceBundleName, locale);
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
