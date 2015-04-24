package JavaServer.responses;

import java.io.*;
import java.util.ArrayList;
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

    public String getDirectoryLinks() {
        String[] fileList = filePath.list();

        String link = "";

        for (String line : fileList) {
            link += "<a href='/"+ line + "'" + ">" + line + "</a>" +
                    "</br>" +
                    "\r\n";
        }
        return link;
    }

    public List<String> convertFilesToPaths() {
        List<String> paths = new ArrayList<>();

        for (String file : getDirectoryFiles()) {
            paths.add("/" + file);
        }

        return paths;
    }

    public List<String> getDirectoryFiles() {
        List<String> directoryFiles;

        String[] filesList = getMainDirectory().list();

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

    public boolean isFileImage() {
        String fileExtension = getFileExtension(filePath);

        if (validImageExtensions().contains(fileExtension)) {
            return true;
        }
        return false;
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }

    private List<String> validImageExtensions() {
        List<String> imageExtensions = new ArrayList<>();

        imageExtensions.add("jpeg");
        imageExtensions.add("jpg");
        imageExtensions.add("gif");
        imageExtensions.add("png");

        return imageExtensions;
    }

    private String getFileExtension(File filepath) {
        String fileName = filepath.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}
