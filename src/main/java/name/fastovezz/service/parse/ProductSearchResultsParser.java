package name.fastovezz.service.parse;

import java.io.IOException;
import java.util.List;

public interface ProductSearchResultsParser<T> {
    List<T> parse(String queryString) throws IOException;
}
