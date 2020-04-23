package com.safetynet.safetynetalerts.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Firestation {


	private static final Logger logger = LogManager.getLogger(Firestation.class);

	private String address;
	private String station;

	/**
	 * Default constructor
	 */
	public Firestation() {
	}

	/**
	 * Constructor with fied
	 * @param address firestation adress
	 * @param station firestation id
	 */

	public Firestation(String address, String station) {

		this.address = address;
		this.station = station;

	}
	
	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}


	public String getStation() {

		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public String toString() {
		logger.trace("start/finish");
		return "Firestation [address=" + address + ", station=" + station + "]";
	}

 
 
}
