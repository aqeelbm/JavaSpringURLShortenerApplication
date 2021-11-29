package com.code.urlshortener.entity;

import lombok.Data;

/**
 * @author Aqeel
 *
 */

@Data
public class ShortenUrl {
	private String fullUrl;
	private String shortUrl;
}
