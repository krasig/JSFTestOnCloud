package com.krasig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

public class GenerateRoadList {

	private static int getRandom() {

		int aEnd = 2;
		int aStart = -2;
		Random aRandom = new Random();
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);

		return randomNumber;

	}

	public static ArrayList<String> generateList(int startDay,int endDay,
			int month,int startKm,
			ArrayList<FuelLoad> loads,ArrayList<Travel> travels) {

		ArrayList<String> result=new ArrayList<String>();
		
		
		long pokazania[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		long tvardi[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		long praznici[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		GregorianCalendar gc = new GregorianCalendar();

		// От кои до кой ден !!!
//		int startDay = 1;
//		int endDay = 31;

		// Месец и година за която ще генерираме
		int year = 2010;
//		int month = 10;

		// от колко км започва листа
//		long startKm = 130676;

		// Зарeждания + крайни километри
//		pokazania[15] = Long.valueOf(131083);
//		pokazania[29] = Long.valueOf(131391);
		for(FuelLoad load:loads){
			pokazania[load.getDay()]=load.getKm();
		}

		// Твърди киломентри - например ходене до Пазарджик в почивен ден
//		tvardi[9] = 54;
//		tvardi[10] = 53;
		for(Travel travel:travels){
			tvardi[travel.getDay()]=travel.getKm();
		}
		
		// praznici[17]=1;

		int first = startDay;
		int last = endDay;
		for (int i = startDay; i <= endDay; i++) {
			if (pokazania[i] > 0) {
				last = i;
				System.out.println("от " + first + "." + month + "." + year
						+ " до " + last + "." + month + "." + year
						+ "  Изминати км: " + (pokazania[i] - startKm) + "("
						+ startKm + "-" + pokazania[i] + ")");
				result.add("от " + first + "." + month + "." + year
						+ " до " + last + "." + month + "." + year
						+ "  Изминати км: " + (pokazania[i] - startKm) + "("
						+ startKm + "-" + pokazania[i] + ")");
				long chekSum = 0;
				int tvardoIzminati = 0;
				for (int j = first; j <= last; j++) {
					tvardoIzminati += tvardi[j];
				}

				int rabotni = 0;
				for (int j = first; j <= last; j++) {
					gc.set(year, month - 1, j);

					int dd = gc.get(Calendar.DAY_OF_WEEK);
					// System.out.println(j + "--->" + dd);
					if (dd != 1 && dd != 7 && tvardi[j] == 0
							&& praznici[j] == 0) {
						// System.out.println(j + " ---> " +tvardi[j]);
						rabotni++;
					}
				}

				// System.out.println("Брой работни : " + rabotni);
				// System.out.println("Твърдо изминати за периода : " +
				// tvardoIzminati);
				// System.out.println("Средно км на ден : ~" + ((pokazania[ i] -
				// startKm-tvardoIzminati)/rabotni));
				long KmToSplit = pokazania[i] - startKm - tvardoIzminati;
				long sredno = KmToSplit / rabotni;
				long all = 0;
				for (int j = first; j < last; j++) {
					gc.set(year, month - 1, j);
					int dd = gc.get(Calendar.DAY_OF_WEEK);
					if (dd != 1 && dd != 7 && tvardi[j] == 0
							&& praznici[j] == 0) {
						long tek = sredno + getRandom();
						System.out.println(j + " -->" + tek);
						result.add(j+" -- "+ tek+ "  София град");
						all += tek;
						chekSum += tek;
					}
					if (tvardi[j] > 0) {
						System.out.println(j + " -->" + tvardi[j]);
						for (int k = 0; k < travels.size(); k++) {
							if (travels.get(k).getDay()==j)
								result.add(j+" -- "+travels.get(k).getKm()+"  "+travels.get(k).getWhere());
							
						}
						chekSum += tvardi[j];
					}

				}
				System.out.println(last + " -->" + (KmToSplit - all));
				result.add(last + " -->" + (KmToSplit - all)+"  София град");
				chekSum += (KmToSplit - all);

				System.out.println(" CS -----> " + chekSum);
				result.add( "Общо:"+ chekSum);
				first = last + 1;
				last = endDay;
				startKm = Long.valueOf(pokazania[i]).intValue();
			}
		}
		return result;

	}
}
