package org.pf9.pangu.framework.data.domain;

/**
 * 可锁定的，如为true，则不可删除
 * 
 */
public interface Lockable {
	public boolean isLocked();

	public void setLocked(boolean locked);

	/**
	 * 标识为已锁定
	 */
	public void markLocked();
}