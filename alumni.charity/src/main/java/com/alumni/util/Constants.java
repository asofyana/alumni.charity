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
		MEMBER_MONTHLY("MEMBER_MONTHLY"),
		NONMEMBER("NONMEMBER"),
		CHARITY_PAYMENT("CHARITY_PAYMENT");
		
		private String type;
		PaymentType(String type) {
			this.type = type;
		}

		@Override
	    public String toString() {
	    	return type;
	    }
	}

	public enum RequestPaymentStatus {
		PENDING_APPROVAL("PENDING_APPROVAL"),
		APPROVED("APPROVED");
		
		private String status;
		RequestPaymentStatus(String status) {
			this.status = status;
		}

		@Override
	    public String toString() {
	    	return status;
	    }
	}

	public enum MemberStatus {
		
		PENDING("PENDING"),
		ACTIVE("ACTIVE"),
		REJECTED("REJECTED");
		
		private String status;
		MemberStatus(String status) {
			this.status = status;
		}

		@Override
	    public String toString() {
	    	return status;
	    }
	}
}
