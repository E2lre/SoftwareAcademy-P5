package com.safetynet.safetynetalerts.model;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;

public class Firestation {

	private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
	private String address;
	//private int station;
	private String station;

	/**
	 * Default constructor
	 */
	public Firestation() {
		//logger.trace("construct vide");
	}

	/**
	 * Constructor with fied
	 * @param address firestation adress
	 * @param station firestation id
	 */
	//public Firestation(String address, int station) {
	public Firestation(String address, String station) {
		//logger.trace("start");
		this.address = address;
		this.station = station;
		//logger.trace("finish");
	}
	
	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	//public int getStation() {
	public String getStation() {

		return station;
	}

	//public void setStation(int station) {
	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public String toString() {
		logger.trace("start/finish");
		return "Firestation [address=" + address + ", station=" + station + "]";
	}

 
 
}
