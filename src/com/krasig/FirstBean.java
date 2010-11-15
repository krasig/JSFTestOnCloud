package com.krasig;

import java.io.Serializable;
import java.util.ArrayList;

public class FirstBean implements Serializable{

	/**
	 * proba
	 */
	private static final long serialVersionUID = 8931228193046062395L;

	private String str="";

	private ArrayList<String> mylist=new ArrayList<String>();
	
	private int[] monts={1,2,3,4,5,6,7,8,9,10,11,12};
	private int selectedMont;
	
	
	public FirstBean(){
		mylist.add("aaa");
	}
	
	public String actionAdd() {
		getMylist().add(String.valueOf(getStr()+"-"+ getMylist().size()));
		System.out.println("aaaaaaa-"+getMylist().size());
		
		return null;
		
	}
	
	
	public void setStr(String str) {
		System.out.println("setStr");
		this.str = str;
	}

	public String getStr() {
		System.out.println("getStr");
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
		this.selectedMont = selectedMont;
	}

	public int getSelectedMont() {
		return selectedMont;
	}

	
	
}
