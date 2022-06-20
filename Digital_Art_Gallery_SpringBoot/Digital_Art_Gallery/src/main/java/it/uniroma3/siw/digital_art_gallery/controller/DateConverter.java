package it.uniroma3.siw.digital_art_gallery.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateConverter {
	
	public static Date convertitoreData(String data) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		
		Date date = sdf.parse(data);
		
		return date;
	}
}
