import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductItemRepository productItemRepository;

    public Collection<ProductItem> searchProducts(String query) {
        Iterable<ProductItem> allItems = productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        for (ProductItem item : allItems) {
            if (matchesSearch(item, query)) {
                itemList.add(item);
            }
        }

        return itemList;
    }

    private boolean matchesSearch(ProductItem item, String query) {
        return nameContainsQuery(item, query) ||
               descriptionContainsQuery(item, query) ||
               categoryEqualsQuery(item, query);
    }

    private boolean nameContainsQuery(ProductItem item, String query) {
        return item.getName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean descriptionContainsQuery(ProductItem item, String query) {
        return item.getDescription().toLowerCase().contains(query.toLowerCase());
    }

    private boolean categoryEqualsQuery(ProductItem item, String query) {
        return item.getCategory().toLowerCase().equals(query.toLowerCase());
    }
}
