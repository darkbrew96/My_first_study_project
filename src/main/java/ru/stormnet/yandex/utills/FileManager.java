package ru.stormnet.yandex.utills;

import java.io.File;

public class FileManager {


    public static void createFile() {
        try {
            File f = new File(PropertiesManager.getProperty("path_to_the_file"));
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");//добавить логирование
        } catch (Exception e) {
            System.err.println(e);//логирование
        }
    }

    public static void deleteFile() {
        File file = new File(PropertiesManager.getProperty("path_to_the_file"));
        if (file.delete()) {
            System.out.println("File deleted successfully");//добавить логирование
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}

