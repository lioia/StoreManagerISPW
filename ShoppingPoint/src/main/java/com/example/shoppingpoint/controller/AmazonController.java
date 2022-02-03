package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.exception.BoundaryException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AmazonController {
    public float getEstimatedPrice(String name) throws BoundaryException {
        String url = "https://amazon.it/s?k=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BoundaryException("Could not load page");
        }
        Elements prices = doc.select("span.a-price-whole");
        int i = 0;
        float total = 0;
        for (Element element : prices) {
//            considering the first 5 results (not everything is exact)
            if(i > 5) break;
            float price = Float.parseFloat(element.text().replace(",", "."));
            if(price > 0) {
                total += price;
                i++;
            }
        }

        return total / 5;
    }
}
