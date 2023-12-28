package com.redis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

@Component
public class Authorization {
	String accessToken = null;
	String tokenSecret = null;
	// get token and secret

	ObjectMapper mapper = new ObjectMapper();

	private static String key = "EQUSER1";
	private static String secret = "U2JOUUJ4L1BjM1U90";

	private static final String HMAC_SHA1 = "HmacSHA1";

	private static final String ENC = "UTF-8";

	private static Base64 base64 = new Base64();

	private static String getSignature(String url, String params)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		StringBuilder base = new StringBuilder();
		base.append("GET&");
		base.append(url);
		base.append("&");
		base.append(params);
		System.out.println("Stirng for oauth_signature generation:" + base);

		byte[] keyBytes = (secret + "&").getBytes(ENC);

		SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);

		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(key);

		return new String(base64.encode(mac.doFinal(base.toString().getBytes(ENC))), ENC).trim();
	}

	public static Authorization getInstance() {
		return new Authorization();
	}

	public void getAccessToken() throws ClientProtocolException, IOException, URISyntaxException, InvalidKeyException,
			NoSuchAlgorithmException {
		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
		qparams.add(new BasicNameValuePair("oauth_nonce", "" + (int) (Math.random() * 100000000)));
		qparams.add(new BasicNameValuePair("oauth_signature_method", "HMAC-SHA1"));
		qparams.add(new BasicNameValuePair("oauth_timestamp", "" + (System.currentTimeMillis() / 1000)));
		qparams.add(new BasicNameValuePair("oauth_version", "1.0"));

		String signature = getSignature(
				URLEncoder.encode("https://eqftt.equitasbank.com/ETCAPICP/api/Authentication/RequestToken", ENC),
				URLEncoder.encode(URLEncodedUtils.format(qparams, ENC), ENC));
		qparams.add(new BasicNameValuePair("oauth_signature", signature));
		URI uri = URIUtils.createURI("https", "eqftt.equitasbank.com", -1, "/ETCAPICP/api/Authentication/RequestToken",
				URLEncodedUtils.format(qparams, ENC), null);

		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpget);
		org.apache.http.HttpEntity entity = response.getEntity();

		String res = EntityUtils.toString((org.apache.http.HttpEntity) entity);
		ObjectNode resNode = (ObjectNode) mapper.readTree(res);
		if (resNode.get("ResponseCode").asInt() == 0) {
			accessToken = resNode.get("ResponseContent").get("Token").asText();
			tokenSecret = resNode.get("ResponseContent").get("TokenSecret").asText();
		}
	}

	public JsonNode callPostAPI(String url, ObjectNode data) throws IOException {
		JsonNode responseNode = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			if (ObjectUtils.isEmpty(accessToken)) {
				this.getAccessToken();
			}
			String consumerKey = "EQUSER1";
			String consumerSecret = "U2JOUUJ4L1BjM1U90";
			
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
			consumer.setTokenWithSecret(accessToken,tokenSecret);	
			String req = consumer.sign(url);
			HttpHeaders httpHeaders = new HttpHeaders();
			
			httpHeaders.add("Content-Type", "application/json");
			httpHeaders.add("Cookie", "TS015779b1=01dc25715c197dd4a0d8fac78bea48d4603f24110c0e8960496705f78825fee36de233c0ebaa94f1af96655cdef92fe40f2f377fec");
			ResponseEntity<String> model;
			req = "https://eqftt.equitasbank.com/ETCAPICP/api/master/getstate?oauth_consumer_key=EQUSER1&oauth_token=dkxQcm9ESlYyMGhHeG5pNTNYRTFUckdaK1UyajdxQjA1&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1684159334&oauth_nonce=6pZDRnTPidT&oauth_version=1.0&oauth_signature=UKssUrYtmVfGsR7JPngKlGRbPJQ%3D";
			
			HttpEntity<?> entity = new HttpEntity<Object>(data, httpHeaders);
			model = restTemplate.exchange(req, HttpMethod.POST , entity, String.class);
			String response = model.getBody();
			responseNode = (ObjectNode) mapper.readTree(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseNode;
	}
	
	public  JsonNode getRequest(String url) {
		JsonNode resp = null;

		try {
			if (ObjectUtils.isEmpty(accessToken)) {
				this.getAccessToken();
			}
			String consumerKey = "EQUSER1";
			String consumerSecret = "U2JOUUJ4L1BjM1U90";
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
			consumer.setTokenWithSecret(accessToken, tokenSecret);
			String uri = url.toString();
			HttpClient httpclient = new DefaultHttpClient();
			String req = consumer.sign(url);
			HttpGet httpget = new HttpGet(req);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = (HttpEntity) response.getEntity();

			String res = EntityUtils.toString((org.apache.http.HttpEntity) entity);
			resp = mapper.readTree(res);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	
}