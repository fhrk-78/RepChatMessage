package io.github.fhrk_78.rcm;

import io.github.fhrk_78.rcm.util.ArrayStringConfigHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepChatMessage implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("repchatmessage");
	public static final String MOD_ID = "repchatmessage";

	public static final ArrayStringConfigHandler BEFORE = new ArrayStringConfigHandler("before");
	public static final ArrayStringConfigHandler AFTER = new ArrayStringConfigHandler("after");

	@Override
	public void onInitialize() {
		LOGGER.info("RepChatMessage: Loaded, Entry Point Main");
		BEFORE.init();
		AFTER.init();
	}
}