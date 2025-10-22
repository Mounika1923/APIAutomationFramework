package requestSpecifications;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.commonUtils;

public class GoogleAPIRequestSpecifications extends commonUtils {
	
	public static RequestSpecification reqs;
	public static RequestSpecification ReqSpec() throws IOException {
		
		if(reqs==null) {
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
		 reqs= new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUri"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		return reqs;
		}
		return reqs;
	}
	


}
