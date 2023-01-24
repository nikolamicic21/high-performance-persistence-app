package io.nikolamicic21.highperformancepersistenceapp.entity.util;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CacheUtil.class);

    public static void printCacheRegionStatistics(SessionFactory emFactory, String region) {
        final var cacheRegionStatistics = emFactory.getStatistics().getCacheRegionStatistics(region);
        LOG.info("\n\nRegion: {},\nStatistics: {}\n",
                region, cacheRegionStatistics);
    }

}
