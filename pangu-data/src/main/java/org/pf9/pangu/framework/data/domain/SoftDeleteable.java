package org.pf9.pangu.framework.data.domain;

import java.time.Instant;

public interface SoftDeleteable {

    boolean isDeleted();

    void setDeleted(boolean deleted);

    void markDeleted();

    void setDeletedDate(Instant deletedDate);

    Instant getDeletedDate();
}
