package JavaServer.responses;

import java.io.*;

public class FileManager {
    private File filePath;

    public FileManager(File filePath) {
        this.filePath = filePath;
    }

    public boolean doesFileExist() {
        return filePath.exists();
    }

    public String getDirectoryListing() {
        return filePath.getAbsolutePath();
    }

    public String getMainDirectory() {
        return filePath.getParentFile().getAbsolutePath();
    }
}
