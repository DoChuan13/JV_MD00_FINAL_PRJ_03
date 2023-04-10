package app.config;

import init.DataBase;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

public final class IOFileConfig<T> {
    private static final String FILE_NOT_FOUND_EXCEPTION = ColorConfig.ERROR_COLOR + "File Not Found Exception" + ColorConfig.RESET;
    private static final String IOE_EXCEPTION = ColorConfig.ERROR_COLOR + "IOE Exception" + ColorConfig.RESET;
    private static final String CLASS_NOT_FOUND_EXCEPTION = ColorConfig.ERROR_COLOR + "Class Not Found Exception" + ColorConfig.RESET;

    private synchronized static void getReferenceFile(File file) throws IOException {
        if (!file.exists()) {
            String dirPath = System.getProperty("user.dir");
            String backupFolder = dirPath + "/" + DataBase.DATABASE;
            String targetFolder = dirPath + "/" + DataBase.PATH_DATABASE;
            new File(DataBase.PATH_DATABASE).mkdirs();
            if (Files.exists(Path.of(backupFolder))) {
                File src = new File(backupFolder);
                File dest = new File(targetFolder);
                Files.move(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                file.createNewFile();
            }
        }
    }

    /*----------------------------------------------------------Read-Write----------------------------------------------------------*/
    public synchronized List<T> readFile(String pathName) {
        List<T> dataList = new LinkedList<>();
        try {
            File file = new File(pathName);
            getReferenceFile(file);
            FileInputStream reader = new FileInputStream(file);
            if (reader.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(reader);
                dataList = (List<T>) objectInputStream.readObject();
                objectInputStream.close();
                reader.close();
            }
        } catch (FileNotFoundException errException) {
            System.out.print(FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException errException) {
            errException.printStackTrace();
            System.out.print(IOE_EXCEPTION);
        } catch (ClassNotFoundException errException) {
            System.out.print(CLASS_NOT_FOUND_EXCEPTION);
        } catch (Exception errException) {
            errException.printStackTrace();
        }
        return dataList;
    }

    public synchronized void writeFile(String pathName, List<T> list) {
        try {
            File file = new File(pathName);
            getReferenceFile(file);
            FileOutputStream writer = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(writer);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();
            writer.close();
        } catch (FileNotFoundException errException) {
            errException.printStackTrace();
            System.out.print(FILE_NOT_FOUND_EXCEPTION);
        } catch (IOException errException) {
            System.out.print(IOE_EXCEPTION);
        } catch (Exception errException) {
            errException.printStackTrace();
        }
    }

    public synchronized void clearFile(String pathName) {
        File file = new File(pathName);
        try {
            getReferenceFile(file);
            FileWriter writer = new FileWriter(file, false);
            PrintWriter print = new PrintWriter(writer, false);
            print.flush();
            print.close();
            writer.close();
        } catch (Exception exception) {
            System.out.println("Exception have been caught");
        }
    }
}