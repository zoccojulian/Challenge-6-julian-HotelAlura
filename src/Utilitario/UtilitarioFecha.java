package Utilitario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class UtilitarioFecha {
	
	public static java.sql.Date tranformarADateSQL(LocalDate fecha) {
		
		
		java.util.Date fechaDate = java.util.Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
		
		return fechaSQL;
	}
	
	public static LocalDate transformarALocalDate(java.util.Date fecha) {
		
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static String DifetenciaEntreFechas(LocalDate ingreso, LocalDate egreso) {
		
		if(ingreso.until(egreso, ChronoUnit.DAYS) <= 0) {
			return "Error en fechas";
		}else return Double.toString(ingreso.until(egreso, ChronoUnit.DAYS));
	}
}
