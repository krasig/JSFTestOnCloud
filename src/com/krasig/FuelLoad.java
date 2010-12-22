package com.krasig;

import java.io.Serializable;

public class FuelLoad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1086523290927028779L;
	private int day;
	private int km;
	public FuelLoad(int day, int km){
		this.day=day;
		this.km=km;
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

}
