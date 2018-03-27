package org.pf9.pangu.framework.common.config.constants;

/**
 * Created by qiulin on 2017/8/13.
 */
public final class ProfileConstants {

    // Spring profiles for development, test and production, see http://jhipster.github.io/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    // Spring profile used to disable swagger
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    // Spring profile used to disable running liquibase
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    public static final String SPRING_PROFILE_SESSION_AUTH = "session";
    public static final String SPRING_PROFILE_JWT_AUTH = "jwt";

    public ProfileConstants() {
    }
}
