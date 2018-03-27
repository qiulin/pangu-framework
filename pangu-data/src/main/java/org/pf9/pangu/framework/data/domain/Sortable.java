package org.pf9.pangu.framework.data.domain;

/**
 * 默认根据sortNo大小进行升序排序
 * 
 */
public interface Sortable {
	Integer getSortNo();

	void setSortNo(Integer sortNo);
}
