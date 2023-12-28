package com.redis;

import java.net.URISyntaxException;
import java.util.Random;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import oauth.signpost.signature.HmacSha1MessageSigner;
import oauth.signpost.signature.OAuthMessageSigner;
import oauth.signpost.signature.SigningStrategy;

public class OauthConsumer  implements OAuthConsumer{


    private static final long serialVersionUID = 1L;

    private String consumerKey, consumerSecret;

    private String token;

    private OAuthMessageSigner messageSigner;

    private SigningStrategy signingStrategy;

    // these are params that may be passed to the consumer directly (i.e.
    // without going through the request object)
    private HttpParameters additionalParameters;

    // these are the params which will be passed to the message signer
    private HttpParameters requestParameters;
    
    private boolean sendEmptyTokens;
    
    final private Random random = new Random(System.nanoTime());
	  
	public OauthConsumer(String consumerKey, String consumerSecret) {
		 this.consumerKey = consumerKey;
	     this.consumerSecret = consumerSecret;
	     setMessageSigner(new HmacSha1MessageSigner());
	     setSigningStrategy(new AuthorizationHeaderSigningStrategy());
	}

	@Override
	public void setMessageSigner(OAuthMessageSigner messageSigner) {
		this.messageSigner = messageSigner;
        messageSigner.setConsumerSecret(consumerSecret);
	}

	@Override
	public void setAdditionalParameters(HttpParameters additionalParameters) {
		 this.additionalParameters = additionalParameters;
		
	}

	@Override
	public void setSigningStrategy(SigningStrategy signingStrategy) {
		 this.signingStrategy = signingStrategy;
		
	}

	@Override
	public void setSendEmptyTokens(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HttpRequest sign(HttpRequest request)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		 if (consumerKey == null) {
	            throw new OAuthExpectationFailedException("consumer key not set");
	        }
	        if (consumerSecret == null) {
	            throw new OAuthExpectationFailedException("consumer secret not set");
	        }

	        requestParameters = new HttpParameters();
	        if (additionalParameters != null) {
			    requestParameters.putAll(additionalParameters, false);
			}
   
			// add any OAuth params that haven't already been set
		//	completeOAuthParameters(requestParameters);
			requestParameters.remove(OAuth.OAUTH_SIGNATURE);

	        String signature = messageSigner.sign(request, requestParameters);
	        OAuth.debugOut("signature", signature);

	        signingStrategy.writeSignature(signature, request, requestParameters);
	        OAuth.debugOut("Request URL", request.getRequestUrl());

	        URIBuilder ub;
			try {
			ub = new URIBuilder(request.getRequestUrl());		
			ub.addParameter(OAuth.OAUTH_CONSUMER_KEY, consumerSecret);
			ub.addParameter(OAuth.OAUTH_SIGNATURE_METHOD,messageSigner.getSignatureMethod());
			ub.addParameter(OAuth.OAUTH_TIMESTAMP, generateTimestamp());
			ub.addParameter(OAuth.OAUTH_NONCE, generateNonce());
			ub.addParameter(OAuth.OAUTH_VERSION, OAuth.VERSION_1_0);
			ub.addParameter(OAuth.OAUTH_SIGNATURE, signature);
			request.setRequestUrl(ub.toString());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
	        return request;
	}

	@Override
	public HttpRequest sign(Object request)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		return null;
	}

	@Override
	public String sign(String url)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		URIBuilder ub;
		String returnUrl = null;
		try {
			ub = new URIBuilder(url);
		
		ub.addParameter(OAuth.OAUTH_CONSUMER_KEY, consumerKey);
		ub.addParameter(OAuth.OAUTH_SIGNATURE_METHOD,"HMAC-SHA1");
		ub.addParameter(OAuth.OAUTH_TIMESTAMP, generateTimestamp());
		ub.addParameter(OAuth.OAUTH_NONCE, generateNonce());
		ub.addParameter(OAuth.OAUTH_VERSION, OAuth.VERSION_1_0);
		ub.addParameter(OAuth.OAUTH_SIGNATURE, "kL35XY63mtpwSsbCtyJSmFsmsoM%3D");
		returnUrl = ub.toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnUrl;
		
	}

	@Override
	public void setTokenWithSecret(String token, String tokenSecret) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConsumerKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConsumerSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpParameters getRequestParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * protected void completeOAuthParameters(HttpParameters out) { if
	 * (!out.containsKey(OAuth.OAUTH_CONSUMER_KEY)) {
	 * out.put(OAuth.OAUTH_CONSUMER_KEY, consumerKey, true); } if
	 * (!out.containsKey(OAuth.OAUTH_SIGNATURE_METHOD)) {
	 * out.put(OAuth.OAUTH_SIGNATURE_METHOD, messageSigner.getSignatureMethod(),
	 * true); } if (!out.containsKey(OAuth.OAUTH_TIMESTAMP)) {
	 * out.put(OAuth.OAUTH_TIMESTAMP, generateTimestamp(), true); } if
	 * (!out.containsKey(OAuth.OAUTH_NONCE)) { out.put(OAuth.OAUTH_NONCE,
	 * generateNonce(), true); } if (!out.containsKey(OAuth.OAUTH_VERSION)) {
	 * out.put(OAuth.OAUTH_VERSION, OAuth.VERSION_1_0, true); } if
	 * (!out.containsKey(OAuth.OAUTH_TOKEN)) { if (token != null &&
	 * !token.equals("") || sendEmptyTokens) { out.put(OAuth.OAUTH_TOKEN, token,
	 * true); } } }
	 */
	  
	  protected String generateTimestamp() {
	        return Long.toString(System.currentTimeMillis() / 1000L);
	    }
	  
	  protected String generateNonce() {
	        return Long.toString(random.nextLong());
	    }

	

}
