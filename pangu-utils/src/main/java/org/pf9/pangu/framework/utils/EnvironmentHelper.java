package org.pf9.pangu.framework.utils;

import org.pf9.pangu.framework.common.config.constants.ProfileConstants;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class EnvironmentHelper {

    private Environment env;

    public EnvironmentHelper(Environment env) {
        this.env = env;
    }

    public boolean isDev() {


        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        return activeProfiles.contains(ProfileConstants.SPRING_PROFILE_DEVELOPMENT);
    }
}
