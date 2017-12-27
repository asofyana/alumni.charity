package com.alumni.util;

public class Constants {

	public static String SESS_USER = "SESS_USER";
	public static String SYSTEM = "SYSTEM";
	
	public static String PAYMENT_TYPE_MEMBER_MONTHLY = "MEMBER_MONTHLY";
	
	public enum PaymentStatus {
		NEW("NEW"),
		VERIFIED("VERIFIED");
		
		private String status;
		
		PaymentStatus(String status) {
			this.status = status;
		}

	    @Override
	    public String toString() {
	    	return status;
	    }
	}
	
	public enum PaymentType {
		MEMBER_MONTHLY("MEMBER_MONTHLY");
		
		private String type;
		PaymentType(String type) {
			this.type = type;
		}

		@Override
	    public String toString() {
	    	return type;
	    }

	}

}
