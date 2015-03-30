package name.fastovezz.model;

import javax.persistence.*;

/**
 * Created by maks on 15/03/15.
 */
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 255)
    private String name;

    @Lob
    @Column
    private String description;

    @Column(length = 255)
    private String price;
    private Integer rating;
    private Integer commentsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    @Override
    public String toString() {
        return "[name: " + name + "; description: " + description + "; price: " + price
                + "; rating: " + rating + "; commentsCount: " + commentsCount + "]";
    }
}
