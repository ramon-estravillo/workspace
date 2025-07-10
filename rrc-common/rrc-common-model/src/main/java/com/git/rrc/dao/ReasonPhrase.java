package com.git.rrc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@SuppressWarnings("all")
public class ReasonPhrase {

    private static MessageSource source;

    @Autowired
    public ReasonPhrase(@Qualifier("messageSourceDao") MessageSource source) {
        ReasonPhrase.source = source;
    }

    public static String valueOf(int value) {
        return valueOf(value, Locale.getDefault());
    }

    public static String valueOf(int value, Locale locale) {
        return valueOf(value, null, locale);
    }

    public static String valueOf(int value, Object[] args, Locale locale) {
        return source.getMessage(String.valueOf(value), args, locale);
    }

}
