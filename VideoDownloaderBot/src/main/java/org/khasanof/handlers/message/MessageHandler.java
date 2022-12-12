package org.khasanof.handlers.message;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.VideoDownloader;
import org.khasanof.entity.instagram.InstagramEntity;
import org.khasanof.entity.instagram.Links;
import org.khasanof.entity.tiktok.TikTokEntity;
import org.khasanof.handlers.IBaseHandler;
import org.khasanof.keyboards.reply.ReplyKeyboard;
import org.khasanof.service.instagram.InstagramService;
import org.khasanof.service.tiktok.TikTokService;
import org.khasanof.utils.emoji.Emojis;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Getter
@Setter
public class MessageHandler implements IBaseHandler {
    @Override
    public void process(Update update, VideoDownloader bot) {
        Long chatID = update.getMessage().getChatId();
        if (update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                String text = "Choose Download one:";
                SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>" + text + "</b>");
                sendMessage.setParseMode("html");
                sendMessage.setReplyMarkup(ReplyKeyboard.enterMenu());
                bot.executeMessage(sendMessage);
            } else if (update.getMessage().getText().equals("Instagram")) {
                String text = "Enter link" + Emojis.POINT_DOWN;
                SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>" + text + "</b>");
                sendMessage.setParseMode("html");
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                bot.executeMessage(sendMessage);
            } else if (update.getMessage().getText().equals("TikTok")) {
                String text = "Enter link" + Emojis.POINT_DOWN;
                SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>" + text + "</b>");
                sendMessage.setParseMode("html");
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                bot.executeMessage(sendMessage);
            } else if (update.getMessage().getText().contains("www.instagram.com")) {
                try {
                    SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>Processing please wait...</b>");
                    sendMessage.setParseMode("html");
                    bot.executeMessage(sendMessage);
                    InstagramEntity instagramEntity = InstagramService.load(update.getMessage().getText());
                    if (instagramEntity.isStatus()) {
                        Links links = instagramEntity.getResponse().getLinks().get(0);
                        if (links.getExt().equals("mp4")) {
                            SendVideo video = new SendVideo(chatID.toString(), new InputFile(links.getUrl()));
                            video.setCaption("@java_video_loader_bot");
                            video.setReplyMarkup(ReplyKeyboard.enterMenu());
                            bot.executeVideo(video);
                        } else {
                            SendPhoto photo = new SendPhoto(chatID.toString(), new InputFile(links.getUrl()));
                            photo.setCaption("@java_video_loader_bot");
                            photo.setReplyMarkup(ReplyKeyboard.enterMenu());
                            bot.executePhoto(photo);
                        }
                    } else {
                        String msg = instagramEntity.getMsg();
                        SendMessage message = new SendMessage(chatID.toString(), msg);
                        message.setReplyMarkup(ReplyKeyboard.enterMenu());
                        bot.executeMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().contains("tiktok.com")) {
                try {
                    SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>processing...</b>");
                    sendMessage.setParseMode("html");
                    bot.executeMessage(sendMessage);
                    TikTokEntity tikTokEntity = TikTokService.load(update.getMessage().getText());
                    String url = tikTokEntity.getVideo().get(0);
                    SendVideo video = new SendVideo(chatID.toString(), new InputFile(url));
                    video.setReplyMarkup(ReplyKeyboard.enterMenu());
                    bot.executeVideo(video);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                DeleteMessage deleteMessage = new DeleteMessage(chatID.toString(), update.getMessage().getMessageId());
                bot.executeMessage(deleteMessage);
            }
        }
    }
}
