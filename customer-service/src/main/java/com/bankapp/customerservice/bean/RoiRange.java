package com.bankapp.customerservice.bean;

public class RoiRange {

	private int maxRoi;
	private int minRoi;

	public RoiRange(int maxRoi, int minRoi) {
		super();
		this.maxRoi = maxRoi;
		this.minRoi = minRoi;
	}

	public int getMaxRoi() {
		return maxRoi;
	}

	public void setMaxRoi(int maxRoi) {
		this.maxRoi = maxRoi;
	}

	public int getMinRoi() {
		return minRoi;
	}

	public void setMinRoi(int minRoi) {
		this.minRoi = minRoi;
	}

	@Override
	public String toString() {
		return "RoiRange [maxRoi=" + maxRoi + ", minRoi=" + minRoi + "]";
	}

}
