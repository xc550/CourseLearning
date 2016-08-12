package com.cl.util;

public class PageManager {
	private int totalsize;
	private int each;
	private int totalcolumn;
	private int nextcolumn;
	private int precolumn;
	private int nowcolumn;
	
	public int getTotalsize() {
		return totalsize;
	}
	
	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}
	
	public int getEach() {
		return each;
	}
	
	public void setEach(int each) {
		this.each = each;
	}
	
	public int getTotalcolumn() {
		return (totalsize % each == 0 ? totalsize / each : totalsize / each + 1);
	}
	
	public int getNextcolumn() {
		return nextcolumn;
	}
	
	public int getPrecolumn() {
		return precolumn;
	}
	
	public int getNowcolumn() {
		return nowcolumn;
	}
	
	public void setNowcolumn(int nowcolumn) {
		this.nowcolumn = nowcolumn;
	}
	
	public void calcPreColumnAndNextColumn() {
		precolumn = nowcolumn - 2;
		nextcolumn = nowcolumn + 2;
		while (precolumn < 1) {
			precolumn++;
			nextcolumn++;
		}
		while (nextcolumn > totalcolumn) {
			precolumn--;
			nextcolumn--;
		}
		while (precolumn < 1) {
			precolumn++;
		}
	}
}
