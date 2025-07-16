package com.git.rrc.constants;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@SuppressWarnings("all")
class ReasonPhraseResolver {

    private static MessageSource errorCodeMessageSource;
    private static MessageSource httpStatusMessageSource;

    public ReasonPhraseResolver(
            @Qualifier("errorCodeReasonPhrase")  MessageSource errorCodeMessageSource,
            @Qualifier("httpStatusReasonPhrase") MessageSource httpStatusMessageSource
    ) {
        ReasonPhraseResolver.errorCodeMessageSource  = errorCodeMessageSource;
        ReasonPhraseResolver.httpStatusMessageSource = httpStatusMessageSource;
    }

    private static MessageSource getMessageSource(Enum<?> targetType) {
        return targetType instanceof ErrorCode
                ? ReasonPhraseResolver.errorCodeMessageSource
                : ReasonPhraseResolver.httpStatusMessageSource;
    }

    private static String getCode(Enum<?> source) {
        return String.valueOf(((StatusCode) source).value());
    }

    public static String valueOf(Enum<?> source) {
        return valueOf(source, Locale.getDefault());
    }

    public static String valueOf(Enum<?> source, Locale locale) {
        return getMessageSource(source).getMessage(getCode(source), null, locale);
    }

    public static String valueOf(Enum<?> source, Object[] args, Locale locale) {
        return getMessageSource(source).getMessage(getCode(source), args, locale);
    }

}
