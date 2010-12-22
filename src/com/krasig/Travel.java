package com.krasig;

import java.io.Serializable;

public class Travel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6877322849824350398L;
	private int day;
	private int km;
	private String where;
	public Travel(int day,int km,String where){
		this.day=day;
		this.km=km;
		this.where=where;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDay() {
		return day;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public int getKm() {
		return km;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getWhere() {
		return where;
	}
}
