package com.lt.body.weixin.model;


public class UserEntity {
	private String id;
	private String userId;
	private String userName;
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String email;
	private String address;
	private String flag;
	private String answerStartTime;
	private String answerEndTime;
	private String isAnswer;
	private String isDraw;
	private String isWin;
	private String prize;
	private int score;

	private String rank ;//排名



	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getIsWin() {
		return isWin;
	}

	public void setIsWin(String isWin) {
		this.isWin = isWin;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}




	public String getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}

	public String getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}


	public String getAnswerStartTime() {
		return answerStartTime;
	}
	public String getAnswerEndTime() {
		return answerEndTime;
	}
	public void setAnswerStartTime(String answerStartTime) {
		this.answerStartTime = answerStartTime;
	}
	public void setAnswerEndTime(String answerEndTime) {
		this.answerEndTime = answerEndTime;
	}
	 
	public String getPhone() {
		return phone;
	}
	public String getFlag() {
		return flag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

}
