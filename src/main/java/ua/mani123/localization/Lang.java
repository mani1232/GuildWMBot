package ua.mani123.localization;

import ua.mani123.localization.languages.RULang;
import ua.mani123.localization.languages.UALang;

public enum Lang {
    EN(new DefaultLang(), "English"),
    RU(new RULang(), "Русский"),
    UA(new UALang(), "Українська");

    final String nativeName;
    final DefaultLang defaultLang;

    Lang(DefaultLang defaultLang, String s) {
        this.nativeName = s;
        this.defaultLang = defaultLang;
    }

    public String getNativeName() {
        return nativeName;
    }

    public DefaultLang getStrings() {
        return defaultLang;
    }
}
