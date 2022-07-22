package idv.blake.shortlink.controller;

import java.net.MalformedURLException;

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
import idv.blake.shortlink.util.HttpRequestUtil;

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
	 * 顯示產生短網址的結果頁面
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

			String result = HttpRequestUtil.getDomainName(request) + "/" + shortlink;

			model.addAttribute("shortlink", result);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return "gen";

	}

	/**
	 * 顯示產生短網址的結果頁面
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

			String domainName = HttpRequestUtil.getDomainName(request);

			response.setStatus(302);
			response.setHeader("Location", domainName);
			return null;
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		return null;

	}

}
