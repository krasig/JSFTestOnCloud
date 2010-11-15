/*
 *   Copyright 2010 http://whileitcompiles.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.whileitcompiles.tutorial.gae.loancalculator.beans;

/**
 * Loan managed bean
 * @author lukas
 * @version 1.0
 */
public class LoanBean {
	//amount of money to borrow
	private double amount;
	//interest rate
	private double interestRate;
	//number of months
	private int noMonths;
	//was credit already calculated?
	private boolean calculated;
	//total cost of credit
	private double totalInterest;
	//total payment
	private double totalPayment;
	//monthly payment
	private double monthlyPayment;
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public int getNoMonths() {
		return noMonths;
	}
	
	public void setNoMonths(int noMonths) {
		this.noMonths = noMonths;
	}
	
	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}
	
	public boolean isCalculated() {
		return calculated;
	}
	
	public String calculate() {
		calculated = true;
		double r = interestRate / 1200;
		double x = Math.pow(1+r, noMonths);
		monthlyPayment = round((amount *  r * x) / (x - 1));
		totalInterest = round((noMonths * monthlyPayment) - amount);
		totalPayment = amount + totalInterest;
		return null;
	}
	
	private double round(double r) {
		return ((double)Math.round(r * 100)) / 100;
	}
	
	public double getTotalPayment() {
		return totalPayment;
	}
	
	public double getTotalInterest() {
		return totalInterest;
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
}
