package app.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public final class CopyFileConfig {
    private static final String COPY_FILE_ERROR = "Copy file Occur";

    public static void copyFile(String sourcePath, String desPath) {
        File sourceFile = new File(sourcePath);
        File desFile = new File(desPath);
        try {
            Files.copy(sourceFile.toPath(), desFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //Files.move(sourceFile.toPath(), desFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception errException) {
            System.err.println(COPY_FILE_ERROR);
        }
    }
}