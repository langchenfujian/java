package com.test.jackson;

public class Birthday {
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public Birthday(String birthday) {
		super();
		this.birthday = birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Birthday [birthday=" + birthday + "]";
	}
	
}
