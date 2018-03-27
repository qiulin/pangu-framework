package org.pf9.pangu.framework.data.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class HierarchicalEntity<T> extends SortEntity {
    private static final long serialVersionUID = 4795899175741576611L;


    @Column(name = "parent_id")
    private T parentId;

    @Column(name = "tree_path", length = 500)
    private String treePath;

    @Column(name = "tree_level")
    private int treeLevel = 0;

    public T getParentId() {

        return parentId;
    }

    public void setParentId(T parentId) {

        this.parentId = parentId;
    }

    public String getTreePath() {

        return treePath;
    }

    public void setTreePath(String treePath) {

        this.treePath = treePath;
    }

    public int getTreeLevel() {

        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {

        this.treeLevel = treeLevel;
    }

    public boolean isRoot() {

        return this.parentId == null;
    }
}

