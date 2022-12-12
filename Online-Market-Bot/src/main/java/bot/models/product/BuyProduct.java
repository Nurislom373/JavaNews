package bot.models.product;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyProduct extends Product {
    private int count;
    private Date buyTime;
}
