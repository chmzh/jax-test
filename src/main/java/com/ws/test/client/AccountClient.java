package com.ws.test.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import com.ws.test.Account;
import com.ws.test.AccountService;
import com.ws.test.AccountServiceEndpoint;



public class AccountClient {
	
	public static void main(String[] args) throws MalformedURLException {
		/*
		URL wsdlURL = new URL("http://localhost:8888/jax/AccountService?WSDL");  
		QName serviceQName = new QName("http://test.ws.com/", "AccountService");  
		Service service = Service.create(wsdlURL, serviceQName);  
	
		QName portQName = new QName("http://test.ws.com/", "AccountServiceEndpointPort");  
		
		AccountService accountService = service.getPort(portQName,AccountService.class);
		accountService.insertAccount(null);
		*/
		
		
		JaxWsPortProxyFactoryBean factoryBean = new JaxWsPortProxyFactoryBean();
		factoryBean.setServiceInterface(AccountServiceEndpoint.class);
		try {
			factoryBean.setWsdlDocumentUrl(new URL("http://localhost:8888/jax/AccountService?WSDL"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factoryBean.setNamespaceUri("http://test.ws.com/");
		factoryBean.setServiceName("AccountService");
		factoryBean.setPortName("AccountServiceEndpointPort");
		
		//Map<String, Object> customProperties = new HashMap<String, Object>();
		//customProperties.put(key, value)
		//factoryBean.setCustomProperties(customProperties);
		
		factoryBean.afterPropertiesSet();
		AccountServiceEndpoint accountService = (AccountServiceEndpoint)factoryBean.getObject();
		Account account = new Account();
		accountService.insertAccount(account);
	}
	
}
