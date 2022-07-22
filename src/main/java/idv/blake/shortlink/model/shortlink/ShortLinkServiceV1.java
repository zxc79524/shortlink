package idv.blake.shortlink.model.shortlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import idv.blake.shortlink.exception.NotFoundException;
import idv.blake.shortlink.model.shortlink.dao.IShortLinkDao;
import idv.blake.shortlink.model.shortlink.entity.ShortLinkDbEntity;
import idv.blake.shortlink.util.ConvertUtil;

@Service("ShortLinkServiceV1")
@Scope("prototype")
public class ShortLinkServiceV1 {

	@Autowired
	IShortLinkDao dao;

	/**
	 * 儲存和產生短網址
	 * 
	 * @param url
	 * @return
	 */
	public String generateShortLink(String url) {

		long timeStamp = System.currentTimeMillis();
		String shortlink = ConvertUtil.hex10To62(timeStamp);

		ShortLinkDbEntity shortLinkDbEntity = new ShortLinkDbEntity();
		shortLinkDbEntity.setShortLink(shortlink);
		shortLinkDbEntity.setUrl(url);

		dao.save(shortLinkDbEntity);

		return shortlink;
	}

	/**
	 * 
	 * 回傳短網址對應的長網址
	 * 
	 * @param shortlink
	 * @return 
	 * @throws NotFoundException 找不到對應的短網址
	 */
	public String getRedirectURL(String shortlink) throws NotFoundException {

		ShortLinkDbEntity shortLinkDbEntity = dao.findByShortLink(shortlink);

		if (shortLinkDbEntity == null) {
			throw new NotFoundException("Not found shortLink");

		}

		return shortLinkDbEntity.getUrl();
	}

}
