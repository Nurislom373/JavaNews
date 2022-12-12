package bot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private Long chatId;
    private String username;
    private String firstName;
    private String lastName;
    private String number;
    private String language;
}
