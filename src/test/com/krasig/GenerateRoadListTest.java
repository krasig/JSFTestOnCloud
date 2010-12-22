package test.com.krasig;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.krasig.FuelLoad;
import com.krasig.GenerateRoadList;
import com.krasig.Travel;

public class GenerateRoadListTest {

	@Test
	public void testGenerateList() {
		ArrayList<FuelLoad> loads=new ArrayList<FuelLoad>();
		loads.add(new FuelLoad(15,131083));
		loads.add(new FuelLoad(29,131391));
		ArrayList<Travel> travels=new ArrayList<Travel>();
		travels.add(new Travel(9,54,"Dupniycca"));
		travels.add(new Travel(10,53,"Razlog"));



		ArrayList<String> generateList = GenerateRoadList.generateList(1, 31, 1, 130676,loads,travels);
		for (String string : generateList) {
			System.out.println(string);
			
		}
	}

}
