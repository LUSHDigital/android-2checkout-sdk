package com.lush.twocheckoutsdk;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.lush.twocheckoutsdk.model.PaymentCardData;

/**
 * Manages the whole flow for contacting the 2Checkout
 * service and tokenising card data against a key.
 *
 * @author Matt Allen
 */
public class TwoCheckout
{
	/**
	 * We need a webview because there isn't a proper API in place for this.
	 * We'll just throw some JS into the frame instead.
	 */
	private WebView mWebview;
	private boolean useTestBackend;
	private String publishableKey, sellerId;
	private OnTokenEncodedCallback callback;

	public TwoCheckout(Context context, String publishableKey, String token)
	{
		this(context, publishableKey, token, true);
	}

	@SuppressWarnings("all")
	public TwoCheckout(Context context, String publishableKey, String sellerId, boolean useTestBackend)
	{
		mWebview = new WebView(context);
		this.useTestBackend = useTestBackend;
		this.sellerId = sellerId;
		this.publishableKey = publishableKey;
		mWebview.addJavascriptInterface(this, "Lush2Checkout");
		mWebview.loadUrl("file:///android_asset/2checkout.html");
	}

	public void serialise(PaymentCardData card, OnTokenEncodedCallback callback)
	{
		this.callback = callback;
		card.setPublishableKey(publishableKey);
		card.setSellerId(sellerId);
		String cardData = new Gson().toJson(card);
		String jsEvent = "javascript:generateToken('" + getEnvironment()+ "','" + cardData + "')";
		mWebview.loadUrl(jsEvent);
	}

	private String getEnvironment()
	{
		return useTestBackend ? "sandbox" : "production";
	}

	@JavascriptInterface
	public void on2CheckoutTokenSuccess(String token)
	{
		if (callback != null)
		{
			callback.onTokenCreated(token);
		}
	}

	@JavascriptInterface
	public void on2CheckoutTokenError(String error)
	{
		// TODO Don't fail silently
	}
}
