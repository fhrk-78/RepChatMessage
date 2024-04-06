package io.github.fhrk_78.rcm.client;

import io.github.fhrk_78.rcm.RepChatMessage;
import net.fabricmc.api.ClientModInitializer;

public class RepChatMessageClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RepChatMessage.LOGGER.info("RepChatMessage: Loaded, Entry Point Client");
    }

    public static String accessRepeatReplace(String y) {
        return repeatReplace(y, RepChatMessage.BEFORE.list.toArray(new String[RepChatMessage.BEFORE.list.size()]),
                RepChatMessage.AFTER.list.toArray(new String[RepChatMessage.BEFORE.list.size()]));
    }

    public static String repeatReplace(String y, String[] a, String[] b) {
        if (a.length == b.length) {
            String s = y;
            int i = 0;
            for (String x : a) {
                s = s.replace(x, b[i]);
                i++;
            }
            return s;
        } else {
            RepChatMessage.LOGGER.error("String ReplaceMethod Exception");
            return y;
        }
    }
}
