package com.bankapp.customerservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("customer-service")
public class RoiConfiguration {

	private int minRoi;
	private int maxRoi;

//	public RoiConfiguration(int minRoi, int maxRoi) {
//		super();
//		this.minRoi = minRoi;
//		this.maxRoi = maxRoi;
//	}

	public int getMinRoi() {
		return minRoi;
	}

	public void setMinRoi(int minRoi) {
		this.minRoi = minRoi;
	}

	public int getMaxRoi() {
		return maxRoi;
	}

	public void setMaxRoi(int maxRoi) {
		this.maxRoi = maxRoi;
	}

}
