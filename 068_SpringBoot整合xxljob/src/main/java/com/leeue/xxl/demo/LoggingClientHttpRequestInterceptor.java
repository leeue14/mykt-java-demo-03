package com.leeue.xxl.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println(("===========================request begin================================================"));
        System.out.println("URI         : " + request.getURI());
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Headers     : " + request.getHeaders());
        System.out.println("Request body: " + new String(body, "UTF-8"));
        System.out.println("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
        }
        System.out.println("============================response begin==========================================");
        System.out.println("Status code  : " + response.getStatusCode());
        System.out.println("Status text  : " + response.getStatusText());
        System.out.println("Headers      : " + response.getHeaders());
        System.out.println("Response body: " + inputStringBuilder.toString());//WARNING: comment out in production to improve performance
        System.out.println("=======================response end=================================================");
    }

}
