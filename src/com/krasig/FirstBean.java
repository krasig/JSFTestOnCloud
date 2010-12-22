package com.krasig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FirstBean implements Serializable {

	/**
	 * proba 2
	 */
	private static final long serialVersionUID = 8931228193046062395L;

	private String str = "";

	private ArrayList<String> mylist = new ArrayList<String>();

	private int[] monts = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	private int selectedMont = 1;
	private int periodStart = 1;
	private int periodEnd = 31;
	private int startKm;

	private int fuelLoadDay;
	private int fuelLoadKm;
	private ArrayList<FuelLoad> fuelLoads = new ArrayList<FuelLoad>();
	
	private int travelDay;
	private int travelKm;
	private String travelWhere;
	private ArrayList<Travel> travelers = new ArrayList<Travel>(); 

	private ArrayList<String> roadList=new ArrayList<String>();
	
	public FirstBean() {
		// mylist.add("aaa");
	}

	public String actionAdd() {
		getMylist().add(String.valueOf(getStr() + "-" + getMylist().size()));
		System.out.println("selectedMont:" + selectedMont);
		System.out.println("periodStart:" + periodStart);
		System.out.println("periodEnd:" + periodEnd);
		System.out.println("fuelLoadDay:" + fuelLoadDay);
		System.out.println("fuelLoadKm:" + fuelLoadKm);

		return null;

	}
	public String actionGenerate() {
		roadList=GenerateRoadList.generateList(periodStart, periodEnd, 
				selectedMont, startKm, fuelLoads, travelers);
		
		return null;
		
	}

	public String actionAddFuel() {
		System.out.println("actionAddFuel");
		fuelLoads.add(new FuelLoad(fuelLoadDay, fuelLoadKm));
		return null;
	}

	public String actionDeleteFuel() {
		System.out.println("actionDeleteFuel");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		String parm = myRequest.getParameter("parmDate");
		if (parm!=null){
			System.out.println("parm value:"+parm);
			for (FuelLoad obj : getFuelLoads()) {
				if (obj.getDay()==Integer.parseInt((parm))){
					System.out.println("remove");
					fuelLoads.remove(obj);
					break;
					
				}
			}
		}
			
		// fuelLoads.remove(selectedRow);
		return null;

	}

	public String actionAddTravel(){
		System.out.println("actionAddTravel:day="+travelDay+" km="+travelKm+" where="+travelWhere);
		travelers.add(new Travel(travelDay, travelKm,this.travelWhere));
		return null;
	}
	
	public String actionDeleteTravel() {
		System.out.println("actionDeleteTravel");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		String parm = myRequest.getParameter("parmDate");
		if (parm!=null){
			System.out.println("parm value:"+parm);
			for (Travel obj : getTravelers()) {
				if (obj.getDay()==Integer.parseInt((parm))){
					System.out.println("remove");
					travelers.remove(obj);
					break;
					
				}
			}
		}
			
		// fuelLoads.remove(selectedRow);
		return null;

	}

	public void setStr(String str) {
		// System.out.println("setStr");
		this.str = str;
	}

	public String getStr() {
		// System.out.println("getStr");
		return str;
	}

	public void setMylist(ArrayList<String> mylist) {
		this.mylist = mylist;
	}

	public ArrayList<String> getMylist() {
		return mylist;
	}

	public int[] getMonts() {
		return monts;
	}

	public void setSelectedMont(int selectedMont) {
		System.out.println("setSelectedMont with arg:" + selectedMont);
		this.selectedMont = selectedMont;
	}

	public int getSelectedMont() {
		return selectedMont;
	}

	public int getPeriodStart() {
		return periodStart;
	}

	public int getPeriodEnd() {
		System.out.println("getPeriodEnd");
		return getMonthMaxDays(selectedMont);
	}

	public static int getMonthMaxDays(int m) {
		System.out.println("Executed getMonthMaxDays with arg:" + m);
		// First get an instance of calendar object.
		Calendar calendar = Calendar.getInstance();

		// We'll set the date of the calendar to the following
		// date. We can use constant variable in the calendar
		// for months value (JANUARY - DECEMBER). Be informed that
		// month in Java started from 0 instead of 1.
		int year = 2010;
		int month = m - 1;
		int date = 1;
		// We have a new date of 2007-02-01
		calendar.set(year, month, date);

		// Here we get the maximum days for the date speficied
		// in the calendar. In this case we want to get the number
		// of days for february 2007
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("Max Day: " + maxDay);

		// // Here we want to see what is the days for february on
		// // a leap year.
		// calendar.set(2004, Calendar.FEBRUARY, 1);
		// maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println("Max Day: " + maxDay);
		return maxDay;
	}

	public void setFuelLoadDay(int fuelLoadDay) {
		System.out.println("setFuelLoadDay with arg:" + fuelLoadDay);
		this.fuelLoadDay = fuelLoadDay;
	}

	public int getFuelLoadDay() {
		return fuelLoadDay;
	}

	public void setFuelLoadKm(int fuelLoadKm) {
		System.out.println("setFuelLoadKm with arg:" + fuelLoadKm);
		this.fuelLoadKm = fuelLoadKm;
	}

	public int getFuelLoadKm() {
		return fuelLoadKm;
	}

	public void setFuelLoads(ArrayList<FuelLoad> fuelLoads) {
		this.fuelLoads = fuelLoads;
	}

	public ArrayList<FuelLoad> getFuelLoads() {
		System.out.println("getFuelLoads size="+fuelLoads.size());
		return fuelLoads;
	}

	public void setTravelers(ArrayList<Travel> travelers) {
		this.travelers = travelers;
	}

	public ArrayList<Travel> getTravelers() {
		return travelers;
	}

	public void setTravelDay(int travelDay) {
		System.out.println("setTravelDay "+travelDay);
		this.travelDay = travelDay;
	}

	public int getTravelDay() {
		return travelDay;
	}

	public void setTravelKm(int travelKm) {
		System.out.println("setTravelKm "+travelKm);
		this.travelKm = travelKm;
	}

	public int getTravelKm() {
		return travelKm;
	}

	public void setTravelWhere(String traveWhere) {
		System.out.println("setTravelWhere "+traveWhere);
		this.travelWhere = traveWhere;
	}

	public String getTravelWhere() {
		return travelWhere;
	}

	public void setStartKm(int startKm) {
		this.startKm = startKm;
	}

	public int getStartKm() {
		return startKm;
	}

	public void setRoadList(ArrayList<String> roadList) {
		this.roadList = roadList;
	}

	public ArrayList<String> getRoadList() {
		return roadList;
	}

}
