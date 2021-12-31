package com.etiya.ReCapProject.core.utilities.services.findex.externalFindexCalculator;

import java.util.Random;

public class FindexCalculator {
	
	public int calculateCustomerFindex() {
		Random random=new Random();
		int tempNumber=random.nextInt(1899)+1;
		return tempNumber;
	}
	

}
