package org.pf9.pangu.framework.auth.security;

/**
 * Security constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    public final static String CHARACTER_ENCODING = "UTF-8";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String ANONYMOUS_AUTHORITY = "anonymous";


    private Constants() {
    }
}
