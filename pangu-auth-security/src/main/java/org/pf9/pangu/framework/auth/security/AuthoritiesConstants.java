package org.pf9.pangu.framework.auth.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ACTION_CREATE = "ACTION_CREATE";

    public static final String ACTION_DELETE = "ACTION_DELETE";

    public static final String ACTION_GET = "ACTION_GET";

    public static final String ACTION_QUERY = "ACTION_QUERY";

    public static final String ACTION_UPDATE = "ACTION_UPDATE";


    private AuthoritiesConstants() {
    }
}
