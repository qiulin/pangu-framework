package org.pf9.pangu.framework.cache;

import org.pf9.pangu.framework.common.config.PanguProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(PanguProperties panguProperties) {
        PanguProperties.Cache.Ehcache ehcache =
                panguProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                        ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                        .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                        .build());
    }

    /*
    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(net.qlin.jzengine.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(net.qlin.jzengine.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(net.qlin.jzengine.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
    */
}
