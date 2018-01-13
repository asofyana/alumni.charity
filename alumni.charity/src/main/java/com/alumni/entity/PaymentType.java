package com.alumni.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_PAYMENT_TYPE",uniqueConstraints = { @UniqueConstraint(columnNames = { "PAYMENT_TYPE" }) })
public class PaymentType {
	
	private String paymentType;
	private String flowType;
	
	@Id
	@Column(name="PAYMENT_TYPE")
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name="FLOW_TYPE")
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	
}
