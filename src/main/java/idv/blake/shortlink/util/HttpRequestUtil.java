package idv.blake.shortlink.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
	public static String getDomainName(HttpServletRequest request) throws MalformedURLException {
		URL requestURL = new URL(request.getRequestURL().toString());
		String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		String domainName = requestURL.getProtocol() + "://" + requestURL.getHost() + port;

		return domainName;
	}
}
