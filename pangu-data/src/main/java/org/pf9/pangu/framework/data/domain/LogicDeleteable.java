package org.pf9.pangu.framework.data.domain;

public interface LogicDeleteable {

	public boolean isDeleted();

	public void setDeleted(boolean deleted);

	/**
	 * 标识为已删除
	 */
	public void markDeleted();

}
