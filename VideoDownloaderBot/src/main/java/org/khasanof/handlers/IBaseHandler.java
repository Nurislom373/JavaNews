package org.khasanof.handlers;

import org.khasanof.VideoDownloader;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface IBaseHandler {
    void process(Update update, VideoDownloader bot);
}
