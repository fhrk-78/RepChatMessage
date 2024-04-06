package io.github.fhrk_78.rcm.util;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

import static io.github.fhrk_78.rcm.RepChatMessage.LOGGER;
import static io.github.fhrk_78.rcm.RepChatMessage.MOD_ID;

public class ArrayStringConfigHandler {
    public ArrayStringConfigHandler(String cid) {
        this.dataCallID = cid;
    }
    public ArrayList<String> list = new ArrayList<>();
    private String dataCallID;
    File file;
    public void init() {
        final Path path = FabricLoader.getInstance().getConfigDir().normalize().resolve(this.dataCallID + "." + MOD_ID + ".txt");
        this.file = new File(path.toString());
        readAll();
    }

    public void addNewRepData(String s) {this.list.add(s);refresh();}

    public void delRepDataFromIndex(int index) {this.list.remove(index);refresh();}

    public String getBeforeRepDataFromIndex(int index) {return this.list.get(index);}

    public void refresh() {writeAll();readAll();}

    protected void writeAll() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(this.file, StandardCharsets.UTF_8);
            for (String s : this.list) pw.println(s);
            pw.close();
        } catch (IOException e) {
            LOGGER.error("RepChatMessage: IOException catched.", e);
        }
    }

    protected void readAll() {
        try {
            if (!this.file.exists()) {
                if (this.file.createNewFile()) {
                    LOGGER.info("New file Created.");
                } else {
                    LOGGER.error("Can't create new file");
                    return;
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8));
            String str;
            while ((str = reader.readLine()) != null) {
                this.list.add(str);
            }
        } catch (IOException e) {
            LOGGER.error("RepChatMessage: IOException catched. ", e);
        }
    }
}
