package com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.i18n;

import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class LocalizedMessageResolver {

    private static final String MESSAGES_BASENAME = "messages";
    private static final String DETAIL_KEY_PREFIX = "error.detail.";

    private LocalizedMessageResolver() {
    }

    public static String resolveMessageOrDefault(String key, String defaultValue, Object... args) {
        try {
            var bundle = ResourceBundle.getBundle(MESSAGES_BASENAME, LocaleContextHolder.getLocale());
            if (!bundle.containsKey(key)) {
                return defaultValue;
            }
            return MessageFormat.format(bundle.getString(key), args);
        } catch (MissingResourceException ex) {
            return defaultValue;
        }
    }


    public static String resolveDetail(String detail) {
        if (detail == null || detail.isBlank()) {
            return "";
        }
        var key = DETAIL_KEY_PREFIX + detail.trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
        return resolveMessageOrDefault(key, detail);
    }
}
