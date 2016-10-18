package com.lush.twocheckoutsdk.model;

/**
 * Encapsulation of the payment card data.
 *
 * @author Matt Allen
 */
public class PaymentCardData
{
	private String publishableKey;
	private String ccNo;
	private String cvv;
	private String sellerId;
	private String expMonth;
	private String expYear;

	public PaymentCardData(String cardNumber, String cvv, String expMonth, String expYear)
	{
		this.ccNo = cardNumber;
		this.cvv = cvv;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public String getPublishableKey()
	{
		return publishableKey;
	}

	public String getCcNo()
	{
		return ccNo;
	}

	public String getCvv()
	{
		return cvv;
	}

	public String getSellerId()
	{
		return sellerId;
	}

	public String getExpMonth()
	{
		return expMonth;
	}

	public String getExpYear()
	{
		return expYear;
	}

	public void setPublishableKey(String publishableKey)
	{
		this.publishableKey = publishableKey;
	}

	public void setSellerId(String sellerId)
	{
		this.sellerId = sellerId;
	}
}
