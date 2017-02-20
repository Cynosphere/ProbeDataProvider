package io.github.elytra.probe.api.impl;

import com.ibm.icu.text.NumberFormat;

import io.github.elytra.probe.api.IUnit;

public class Unit implements IUnit {
	private final String name;
	private final String abbreviation;
	private final int barColor;
	
	public Unit(String name, String abbreviation) {
		this(name, abbreviation, 0xAAAAAA);
	}
	
	public Unit(String name, String abbreviation, int barColor) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.barColor = barColor;
	}
	
	@Override
	public String getFullName() {
		return name;
	}

	@Override
	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public int getBarColor() {
		return barColor;
	}

	/* Convenience Methods */
	
	private static final double YOTTA = 1_000_000_000_000_000_000_000_000D;
	private static final double ZETTA = 1_000_000_000_000_000_000_000D;
	private static final double EXA   = 1_000_000_000_000_000_000D;
	private static final double PETA  = 1_000_000_000_000_000D;
	private static final double TERA  = 1_000_000_000_000D;
	private static final double GIGA  = 1_000_000_000D;
	private static final double MEGA  = 1_000_000D;
	private static final double KILO  = 1_000D;
	private static final NumberFormat FORMAT = NumberFormat.getNumberInstance();
	static {
		FORMAT.setMinimumFractionDigits(0);
		FORMAT.setMaximumFractionDigits(2);
	}
	
	public static String formatSI(double d, IUnit u) {
		if (d>YOTTA) {
			return FORMAT.format(d/YOTTA)+" Y"+u.getAbbreviation();
		} else if (d>ZETTA) {
			return FORMAT.format(d/ZETTA)+" Z"+u.getAbbreviation();
		} else if (d>EXA) {
			return FORMAT.format(d/EXA)+" E"+u.getAbbreviation();
		} else if (d>PETA) {
			return FORMAT.format(d/PETA)+" P"+u.getAbbreviation();
		} else if (d>TERA) {
			return FORMAT.format(d/TERA)+" T"+u.getAbbreviation();
		} else if (d>GIGA) {
			return FORMAT.format(d/GIGA)+" G"+u.getAbbreviation();
		} else if (d>MEGA) {
			return FORMAT.format(d/MEGA)+" M"+u.getAbbreviation();
		} else if (d>KILO) {
			return FORMAT.format(d/KILO)+" k"+u.getAbbreviation();
		} else if (d<1.0) {
			//TODO: Perhaps descend down the milli/micro/nano/pico ladder
			return FORMAT.format(d)+" "+u.getAbbreviation();
		} else {
			return FORMAT.format(d)+" "+u.getAbbreviation();
		}
	}
}
