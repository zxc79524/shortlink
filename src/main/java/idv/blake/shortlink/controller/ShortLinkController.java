package idv.blake.shortlink.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import idv.blake.shortlink.exception.NotFoundException;
import idv.blake.shortlink.model.shortlink.ShortLinkServiceV1;

@Controller
public class ShortLinkController {

	@Autowired
	@Resource(name = "ShortLinkServiceV1")
	private ShortLinkServiceV1 shortLinkServiceV1;

	/**
	 * 主頁面
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String index(Model model) {
		return "index";

	}

	/**
	 * 顯示產生短連結的結果頁面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gen", method = { RequestMethod.POST })
	public String generateShortLink(HttpServletRequest request, Model model) {

		try {

			String url = request.getParameter("url");
			String shortlink = shortLinkServiceV1.generateShortLink(url);

			URL requestURL = new URL(request.getRequestURL().toString());
			String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
			String domainName = requestURL.getProtocol() + "://" + requestURL.getHost() + port + "/" + shortlink;

			model.addAttribute("shortlink", domainName);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return "gen";

	}

	/**
	 * 顯示產生短連結的結果頁面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{shortlink}", method = { RequestMethod.GET })
	public String redirectURl(@PathVariable("shortlink") String shortlink, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		try {

			String urlString = shortLinkServiceV1.getRedirectURL(shortlink);

			response.setStatus(302);
			response.setHeader("Location", urlString);
			return null;
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			URL requestURL = new URL(request.getRequestURL().toString());
			String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
			String domainName = requestURL.getProtocol() + "://" + requestURL.getHost() + port;
			response.setStatus(302);
			response.setHeader("Location", domainName);
			return null;
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		return null;

	}

}
