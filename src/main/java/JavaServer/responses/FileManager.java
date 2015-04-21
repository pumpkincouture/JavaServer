package JavaServer.responses;

import java.io.File;

public class FileManager {
    private File filePath;

    public FileManager(File filePath) {
        this.filePath = filePath;
    }


    public boolean doesFileExist() {
        return filePath.exists();
    }
}
