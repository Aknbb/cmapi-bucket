package com.aknbb.cmapibucket.initializer;

import com.aknbb.cmapibucket.pojo.Cmapi;
import com.aknbb.cmapibucket.pojo.CmapiBucket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.function.Consumer;

@Component
@Order(0)
public class DataInitializer implements ApplicationRunner {
    private static final Logger LOGGER = LogManager.getLogger(DataInitializer.class);
    private static Path dataDirPath;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static FileWriter fileWriter;
    public static CmapiBucket cmapiBucket;
    @Autowired
    ServletContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String overridenPath = System.getenv("CMAPI_DATA_DIR");
        String defaultPath = context.getRealPath(File.separator);
        if (overridenPath == null) {
            LOGGER.info("CMAPI_DATA_DIR is not defined.");
            dataDirPath = Paths.get(defaultPath + File.separator + "data");
        } else {
            LOGGER.info("CMAPI_DATA_DIR is: " + overridenPath);
            dataDirPath = Paths.get(overridenPath);
        }
        boolean dataDirExist = Files.isDirectory(dataDirPath);
        if (dataDirExist) {
            LOGGER.info("Data files are already exist. Skipping extract process.");
        } else {
            LOGGER.info("Extracting default data files to " + dataDirPath);
            Path resourceDataDirPath = Paths.get(new ClassPathResource("data_dir").getFile().getAbsolutePath());
            Files.walk(resourceDataDirPath)
                    .parallel()
                    .forEach(sourcePath -> {
                        try {
                            Path targetPath = dataDirPath.resolve(resourceDataDirPath.relativize(sourcePath));
                            LOGGER.debug(String.format("Copying %s to %s%n", sourcePath, targetPath));
                            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception ex) {
                            LOGGER.error(String.format("I/O error: %s%n", ex));
                        }
                    });
        }
        loadMessages();
    }

    private static boolean loadMessages() {
        try {
            cmapiBucket = new CmapiBucket();
            Files.walk(dataDirPath)
                    .parallel()
                    .filter(file -> {
                        String path = file.toAbsolutePath().toString().toLowerCase();
                        return path.endsWith(".json") || path.endsWith(".txt");
                    }).forEach(readCmapiMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Consumer<Path> readCmapiMessage = file -> {
        try {
            Cmapi cmapiMessage = objectMapper.readValue(new File(file.toUri()), Cmapi.class);
            LOGGER.debug(cmapiMessage);
            cmapiBucket.addMessage(cmapiMessage);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    };

    public static Path getDataDirPath() {
        return dataDirPath;
    }

    public static boolean removeMessage(String channel, String title) {
        String filePath = getDataDirPath() + File.separator + channel + File.separator + title;
        File messageFile = new File(filePath + ".txt");
        if (!messageFile.exists()) {
            messageFile = new File(filePath + ".json");
        }
        return messageFile.delete();
    }

    public static boolean removeDirectory(String directoryName) {
        String directoryPath = getDataDirPath() + File.separator + directoryName;
        try {
            Files.walk(Paths.get(directoryPath))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addMessage(Cmapi message) {
        String channel = message.getChannel();
        String title = message.getTitle();
        String filePath = getDataDirPath() + File.separator + channel + File.separator + title + ".txt";
        try {
            Files.createDirectories(Paths.get(getDataDirPath() + File.separator + channel));
            fileWriter = new FileWriter(filePath);
            String output = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
            fileWriter.write(output);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
