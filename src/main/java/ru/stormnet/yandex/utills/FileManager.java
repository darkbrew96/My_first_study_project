package ru.stormnet.yandex.utills;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.stormnet.yandex.pages.AbstractPage;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class FileManager {
    public static final Logger logger = LogManager.getLogger(AbstractPage.class);

    public static char[] generateNameForFile(int len) {
        String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();
        char[] nameForFile = new char[10];
        for (int i = 0; i < len; i++) {
            nameForFile[i] = passSymbols.charAt(rnd.nextInt(passSymbols.length()));
        }
        return nameForFile;
    }

    public static File createFile() {
        File dir = new File("temporaryFolderWithFile");
        boolean isCreated = dir.mkdirs();
        File f = new File((dir.getAbsolutePath() + "\\" + String.valueOf(generateNameForFile(10)) + ".txt"));
        try {
            if (f.createNewFile())
                logger.info("file is created!");
            else
                logger.info("file already exists");
        } catch (Exception e) {
            logger.info(e);
        }
        return f;
    }

    public static void deleteFile() throws IOException {
        File dir = new File("temporaryFolderWithFile");
        FileUtils.deleteDirectory(new File("temporaryFolderWithFile"));

        if (dir.exists()) {
            logger.info("***Failed to delete the file***");
        } else {
            logger.info("file deleted successfully");
        }
    }
}
