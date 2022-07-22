package idv.blake.shortlink.model.shortlink.dao;

import org.springframework.data.repository.CrudRepository;

import idv.blake.shortlink.model.shortlink.entity.ShortLinkDbEntity;

public interface IShortLinkDao extends CrudRepository<ShortLinkDbEntity, String> {

	ShortLinkDbEntity findByShortLink(String shortLink);

}
