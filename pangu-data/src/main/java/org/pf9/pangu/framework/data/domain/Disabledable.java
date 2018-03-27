package org.pf9.pangu.framework.data.domain;

import java.time.Instant;

/**
 * Created by qiulin on 2017/3/1.
 */
public interface Disabledable {

    public boolean isDisabled();

    public void setDisabled(boolean disabled);

    /**
     * 标识为已锁定
     */
    public void markDisabled();

    void setDisabledDate(Instant disabledDate);

    Instant getDisabledDate();
}
