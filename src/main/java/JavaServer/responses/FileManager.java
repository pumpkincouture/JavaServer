package JavaServer.responses;

import java.io.*;
import java.util.Arrays;
import java.util.List;

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

    public String getDirectoryLinks() {
        String[] fileList = filePath.list();

        String link = "";

        for (String line : fileList) {
            link += "<a href='/"+ line + "'" + ">" + line + "</a>" + "\r\n";
        }

        return link;
    }

    public List<String> getDirectoryFiles() {
        List<String> directoryFiles;

        String[] filesList = filePath.list();

        directoryFiles = Arrays.asList(filesList);

        return directoryFiles;
    }

    public String getFileContents()  {
        String fileContent = "";
        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));

            String endOfLines = null;

            while ((endOfLines = br.readLine()) != null) {
                fileContent += endOfLines + "\r\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }
}
