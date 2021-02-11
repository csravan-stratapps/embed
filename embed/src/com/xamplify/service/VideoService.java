package com.xamplify.service;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class VideoService {

    
    public JSONObject getVideo(final String shortenUrlAlias, final String titleAlias) throws IOException {
        final String url = "https://aravindu.com/xtremand-rest/videos/video-by-shortenerurlalias?shortenUrlAlias=" + shortenUrlAlias + "&titleAlias=" + titleAlias;
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozilla/5.0");
        final HttpResponse response = client.execute((HttpUriRequest)request);
        return this.handleResponse(response);
    }
    
    public JSONObject getVideo(final String shortenUrlAlias) throws IOException {
        final String url = "https://aravindu.com/xtremand-rest/videos/video-by-shortenerurlalias?shortenUrlAlias=" + shortenUrlAlias;
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozilla/5.0");
        final HttpResponse response = client.execute((HttpUriRequest)request);
        return this.handleResponse(response);
    }
    
    @SuppressWarnings("unchecked")
	public JSONObject handleResponse(final HttpResponse response) {
        final int status = response.getStatusLine().getStatusCode();
        JSONObject returnData = new JSONObject();
        final JSONParser parser = new JSONParser();
        if (status >= 200 && status < 300) {
            final HttpEntity entity = response.getEntity();
            try {
                if (entity == null) {
                    returnData.put((Object)"status_code", (Object)"1");
                    returnData.put((Object)"error_message", (Object)"null Data Found");
                }
                else {
                    returnData = (JSONObject)parser.parse(EntityUtils.toString(entity));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                returnData.put((Object)"status_code", (Object)"1");
                returnData.put((Object)"error_message", (Object)e.getMessage());
            }
        }
        else {
            returnData.put((Object)"status_code", (Object)"1");
            returnData.put((Object)"error_message", (Object)("Unexpected response status: " + status));
        }
        return returnData;
    }
}