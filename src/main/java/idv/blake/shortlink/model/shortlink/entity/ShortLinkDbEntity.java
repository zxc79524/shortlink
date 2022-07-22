package idv.blake.shortlink.model.shortlink.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHORT_LINK")
public class ShortLinkDbEntity {

	@Id
	@Column(name = "SHORT_LINK", nullable = false, columnDefinition = "varchar(8)")
	private String shortLink;

	@Column(name = "URL", nullable = false, columnDefinition = "nvarchar(2000)")
	private String url;

	public ShortLinkDbEntity() {
	}

	public ShortLinkDbEntity(String shortLink, String url) {
		this.shortLink = shortLink;
		this.url = url;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
