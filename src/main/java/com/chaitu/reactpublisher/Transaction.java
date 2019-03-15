package com.chaitu.reactpublisher;

public class Transaction {
	
	private String txn_id;
	
	private String cust_nbr;
	
	private String cust_nm;
	
	private String txn_dt;
	
	private String revenue_amt;
	
	private String no_of_pckgs;
	
	private String region;

	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}

	public String getCust_nbr() {
		return cust_nbr;
	}

	public void setCust_nbr(String cust_nbr) {
		this.cust_nbr = cust_nbr;
	}

	public String getCust_nm() {
		return cust_nm;
	}

	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}

	public String getTxn_dt() {
		return txn_dt;
	}

	public void setTxn_dt(String txn_dt) {
		this.txn_dt = txn_dt;
	}

	public String getRevenue_amt() {
		return revenue_amt;
	}

	public void setRevenue_amt(String revenue_amt) {
		this.revenue_amt = revenue_amt;
	}

	public String getNo_of_pckgs() {
		return no_of_pckgs;
	}

	public void setNo_of_pckgs(String no_of_pckgs) {
		this.no_of_pckgs = no_of_pckgs;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	@Override
	public String toString() {
		return "txn_id : " + txn_id + " cust_nbr: " + cust_nbr + " cust_nm: " + cust_nm 
					+ " txn_dt: " + txn_dt + " revenue_amt: " + revenue_amt + " no_of_pkgs : " 
						+ no_of_pckgs + " region: " + region;
	}

}
