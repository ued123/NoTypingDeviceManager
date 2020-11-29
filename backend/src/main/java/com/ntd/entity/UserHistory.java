package com.ntd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_history")
public class UserHistory {

	@Id
	@Column(name = "history_id")
	private long historyId;

	@Column(name = "user_date")
	private String userDate;

	@Column(name = "user_history_type")
	private String userHistoryType;

	@Column(name = "user_history")
	private String userHistory;

	public long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getUserHistoryType() {
		return userHistoryType;
	}

	public void setUserHistoryType(String userHistoryType) {
		this.userHistoryType = userHistoryType;
	}

	public String getUserHistory() {
		return userHistory;
	}

	public void setUserHistory(String userHistory) {
		this.userHistory = userHistory;
	}

}
