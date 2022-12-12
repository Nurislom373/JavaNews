package bot.models.product;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountProduct {
    private Long id;
    private Long productId;
    private long discount_price;
}
