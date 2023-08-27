package com.example.revision.design;

import java.util.HashMap;
import java.util.Map;

public class TinyUrlService {

    public static String BASE_HOST = "http://tinyurl.com/";
    public static String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Map<String, String> longToShortUrlsMap = new HashMap<>();
    Map<String, String> shortToLongUrlMapping = new HashMap<>();

    /**
     * To convert longUrl to shortUrl and store the mappings for caching
     *
     * @param longUrl
     * @return
     */
    public String encode(String longUrl) {

        if (longToShortUrlsMap.containsKey(longUrl)) {
            return BASE_HOST + longToShortUrlsMap.get(longUrl);
        }

        String finalShortUrl = null;

        do {
            StringBuilder shortUrl = new StringBuilder();
            // 62^6 -> shortUrls we can entertain
            for (int i = 0; i < 6; i++) {
                int randomIndex = (int) (Math.random() * CHARSET.length());
                shortUrl.append(CHARSET.charAt(randomIndex));
            }
            finalShortUrl = shortUrl.toString();
        } while (shortToLongUrlMapping.containsKey(longUrl));

        longToShortUrlsMap.put(longUrl, finalShortUrl);
        shortToLongUrlMapping.put(finalShortUrl, longUrl);
        return BASE_HOST + finalShortUrl;
    }

    /**
     * To return long url for shortUrl passed
     *
     * @param shortUrl
     * @return
     */
    public String decode(String shortUrl) {
        String shortUrlKey = shortUrl.replaceAll(BASE_HOST, "");
        return shortToLongUrlMapping.get(shortUrlKey);
    }

    public static void main(String[] args) {
        TinyUrlService tinyUrlService = new TinyUrlService();
        String shortUrl = tinyUrlService.encode("https://paytm.com/problems/design-tinyurl");
        System.out.println("Short Url constructed is : " + shortUrl);
        System.out.println("Original Url was : " + tinyUrlService.decode(shortUrl));
    }
}
