package com.spring.elk.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.spring.elk.ElkDemoApplication;
import com.spring.elk.controller.ElkController;

public class LambdaHandler  implements RequestStreamHandler{

	private static final Logger Log= LogManager.getLogger(ElkController.class);
	
	
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	
	static {
		try {
			handler=SpringBootLambdaContainerHandler.getAwsProxyHandler(ElkDemoApplication.class);
		}catch(ContainerInitializationException ex){
			ex.printStackTrace();
			throw new RuntimeException("Could not intialize SpringBoot application");
		}
	}
	
	
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub
	
		Log.info("Inside handlerRequest of Lambda Function.. calling proxy stream");
		handler.proxyStream(input, output, context);
		
		Log.info(" Proxy stream call has completed..");
		output.close();
		
	}
	
	

}
