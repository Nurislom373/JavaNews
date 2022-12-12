package bot.models.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShowProduct {
    private String productName;
    private int count;
    private Long totalPrice;
    private String buyTime;
}
