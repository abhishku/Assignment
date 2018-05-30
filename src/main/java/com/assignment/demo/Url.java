package com.assignment.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Url {
	public Url(Long accountId, StringBuilder longUrl, StringBuilder shortUrl, Long hitcounts) {
		super();
		this.accountId = accountId;
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.hitcounts = hitcounts;
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long requestId;
    private Long accountId;
    private StringBuilder longUrl;
    private StringBuilder shortUrl;
    private Long hitcounts=0l;
	public Long getHitcounts() {
		return hitcounts;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public void setHitcounts(Long hitcounts) {
		this.hitcounts = hitcounts;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public StringBuilder getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(StringBuilder longUrl) {
		this.longUrl = longUrl;
	}
	public Url() {
		super();
	}
	
	@Override
	public String toString() {
		return "Url [requestId=" + requestId + ", accountId=" + accountId + ", longUrl=" + longUrl + ", shortUrl="
				+ shortUrl + ", hitcounts=" + hitcounts + "]";
	}
	public StringBuilder getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(StringBuilder shortUrl) {
		this.shortUrl = shortUrl;
	}

}
