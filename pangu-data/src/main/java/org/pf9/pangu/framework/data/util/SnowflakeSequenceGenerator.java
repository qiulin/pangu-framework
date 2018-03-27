package org.pf9.pangu.framework.data.util;

import org.pf9.pangu.framework.common.config.PanguProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("snowflakeSequenceGenerator")
public class SnowflakeSequenceGenerator implements SequenceGenerator {

    @Autowired
    private PanguProperties panguProperties;

    @Override
    public Long nextValue() {
        return new SnowFlake(panguProperties.getSnowflake().getDatacenter(),
                panguProperties.getSnowflake().getMachine()).nextId();

    }
}
