package com.xinhuo.test;

import java.util.Date;

import org.junit.Test;


public class DateStringTest {
		@Test
		public void DateStringLength(){
			Date date = new Date();
			System.out.println(date.toString().length());
		}
}
