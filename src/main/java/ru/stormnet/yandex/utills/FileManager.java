package ru.stormnet.yandex.utills;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.stormnet.yandex.pages.AbstractPage;

import java.io.File;


public class FileManager {
    public static final Logger logger = LogManager.getLogger(AbstractPage.class);

    public static void createFile() {
        try {
            File f = new File(PropertiesManager.getProperty("path_to_the_file"));
            if (f.createNewFile())
                logger.info("file is created!");
            else
                logger.info("File already exists");
        } catch (Exception e) {
            logger.info(e);
        }
    }

    public static void deleteFile() {
        File file = new File(PropertiesManager.getProperty("path_to_the_file"));
        if (file.delete()) {
            logger.info("***File deleted successfully***");
        } else {
            logger.info("***Failed to delete the file***");
        }
    }
}
