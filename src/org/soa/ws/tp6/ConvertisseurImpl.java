package org.soa.ws.tp6;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;


@WebService(endpointInterface = "org.soa.ws.tp6.Convertisseur")
public class ConvertisseurImpl implements Convertisseur {
	
	@Resource
	WebServiceContext wsContext;
	
	@Override
	@WebMethod
	public double getDinarFromEuro(double euro) {
		
		if(isAuthenticated())
		    return euro * 180;
		else
			throw new HTTPException(401);
		
	}

	private boolean isAuthenticated() {
		
		//1
		MessageContext msgContext = wsContext.getMessageContext();
		
		//2
		Map httpHeaders = (Map)msgContext.get(MessageContext.HTTP_REQUEST_HEADERS);

		//3
		List usernameList = (List) httpHeaders.get("Username");
		List passwordList = (List) httpHeaders.get("Password");

		if(usernameList.contains("blabla") && passwordList.contains("soaws"))
			return true;
	
		return false;
	}

	@Override
	@WebMethod
	public double getEuroFromDinar(double dinar) {
		return dinar / 180;
	}

}
