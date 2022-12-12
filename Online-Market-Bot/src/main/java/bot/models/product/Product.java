package bot.models.product;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "childBuilder")
@ToString
public class Product {
    private Long id;
    private String name;
    private String desc;
    private String imagePath;
    private long price;
}
