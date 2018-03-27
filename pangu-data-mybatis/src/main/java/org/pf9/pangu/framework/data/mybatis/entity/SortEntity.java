package org.pf9.pangu.framework.data.mybatis.entity;


import org.pf9.pangu.framework.data.domain.AbstractAuditingEntity;
import org.pf9.pangu.framework.data.domain.Sortable;

import javax.persistence.Column;

public class SortEntity extends AbstractAuditingEntity implements Sortable {
    private static final long serialVersionUID = -512099056914573545L;

    @Column(name = "sort_no")
    private Integer sortNo;


    public Integer getSortNo() {

        return this.sortNo;
    }

    public void setSortNo(Integer sortNo) {

        this.sortNo = sortNo;
    }
}
