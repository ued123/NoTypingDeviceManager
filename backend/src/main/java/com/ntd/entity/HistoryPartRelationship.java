package com.ntd.entity;

import javax.persistence.*;

@Entity
@Table(name = "history_part_relationship")
public class HistoryPartRelationship {

	@Id
	@Column(name = "REL_ID")
	private long relId;

	@ManyToOne(targetEntity = Device.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PART_ID")
	private Part part;

	@ManyToOne(targetEntity = Part.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "HISTORY_ID")
	private UserHistory userHistory;

	public long getRelId() {
		return relId;
	}

	public void setRelId(long relId) {
		this.relId = relId;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public UserHistory getUserHistory() {
		return userHistory;
	}

	public void setUserHistory(UserHistory userHistory) {
		this.userHistory = userHistory;
	}

}
