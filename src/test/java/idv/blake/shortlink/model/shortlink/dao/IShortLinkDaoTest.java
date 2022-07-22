package idv.blake.shortlink.model.shortlink.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import idv.blake.shortlink.BaseUnitTest;
import idv.blake.shortlink.model.shortlink.entity.ShortLinkDbEntity;

class IShortLinkDaoTest extends BaseUnitTest {

	@Autowired
	IShortLinkDao dao;

	@Test
	void test() {
		ShortLinkDbEntity result = dao.findByShortLink("gewgewg");
		
		assertNull(result);

		dao.save(new ShortLinkDbEntity("aqwrasgd", "https://www.google.com/"));
		result = dao.findByShortLink("aqwrasgd");
		
		assertEquals("aqwrasgd", result.getShortLink());
		assertEquals("https://www.google.com/", result.getUrl());
		
	}

}
