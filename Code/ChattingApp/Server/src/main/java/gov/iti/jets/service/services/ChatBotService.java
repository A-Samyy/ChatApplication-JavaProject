package gov.iti.jets.service.services;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

public class ChatBotService {
    ChatterBotFactory factory = new ChatterBotFactory();
    ChatterBot bot = null;

    public ChatBotService() {
    }

    public String chatBotReply(String msg) {
        String reply="";
        try {
            bot = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
            ChatterBotSession botSession = bot.createSession();
            reply = botSession.think(msg);
            System.out.println("bot> " + reply);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
