package name.fastovezz.service.parse.impl;

import name.fastovezz.model.Product;
import name.fastovezz.service.parse.ProductSearchResultsParser;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RozetkaProductSearchParser implements ProductSearchResultsParser<Product> {

    @Value("${rozetka.search.url}")
    private String searchUrl;

    @Value("${rozetka.css.selector.product}")
    private String productCssSelector;

    @Value("${rozetka.css.selector.product.title}")
    private String productTitleCssSelector;

    @Value("${rozetka.css.selector.product.description}")
    private String productDescriptionCssSelector;

    @Value("${rozetka.css.selector.product.price}")
    private String productPriceCssSelector;

    @Value("${rozetka.css.selector.product.rating}")
    private String productRatingCssSelector;

    @Value("${rozetka.css.selector.product.commentsCount}")
    private String productCommentsCountCssSelector;

    @Override
    public List<Product> parse(String queryString) throws IOException {
        String url = MessageFormat.format(searchUrl, URLEncoder.encode(queryString, "UTF-8"));
        log().debug("Fetching {}", url);

        Document doc = Jsoup.connect(url).get();
        Elements products = doc.select(productCssSelector);
        List<Product> productsList = new ArrayList<Product>(products.size());

        for (Element product : products) {
            Elements title = product.select(productTitleCssSelector);
            Elements description = product.select(productDescriptionCssSelector);
            Elements price = product.select(productPriceCssSelector);
            Elements commentsCount = product.select(productCommentsCountCssSelector);
            Elements rating = product.select(productRatingCssSelector);

            Product productPojo = new Product();
            productPojo.setName(title.size() > 0 ? title.get(0).text() : "");
            productPojo.setDescription(description.size() > 0 ? description.get(0).text() : "");
            productPojo.setPrice(price.size() > 0 ? StringEscapeUtils.unescapeHtml4(price.get(0).text()) : "");

            String commentsCountStr = commentsCount.size() > 0 ? commentsCount.get(0).text().replaceAll("\\D", ""): null;
            Integer commentsCountInt = commentsCountStr != null && commentsCountStr.length() >=1 ? Integer.valueOf(commentsCountStr) : null;
            productPojo.setCommentsCount(commentsCountInt);

            String ratingStr = rating.size() > 0 ? rating.get(0).attr("style").replaceAll("\\D", ""): null;
            Integer ratingInt = ratingStr != null && ratingStr.length() >=1 ? Integer.valueOf(ratingStr) : null;
            productPojo.setRating(ratingInt);

            productsList.add(productPojo);
        }
        return productsList;
    }

    private enum LogSingleton {
        INSTANCE;
        @SuppressWarnings("NonSerializableFieldInSerializableClass")
        private final Logger value = LoggerFactory.getLogger(RozetkaProductSearchParser.class);
    }
    private static Logger log() {
        return LogSingleton.INSTANCE.value;
    }

}
