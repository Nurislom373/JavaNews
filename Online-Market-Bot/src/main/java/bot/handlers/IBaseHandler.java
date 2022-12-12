package bot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IBaseHandler {
    void process(Update update);
}
