package JavaServer.responses;

import javax.activation.MimetypesFileTypeMap;
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

    public File getFilePath() {
        return filePath;
    }

    public String getDirectoryLinks() {
        String[] fileList = filePath.list();

        String link = "";

        for (String line : fileList) {
            link += "<a href='/"+ line + "'" + ">" + line + "</a>" + "\r\n";
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
        String mimetype = new MimetypesFileTypeMap().getContentType(filePath);
        String type = mimetype.split("/")[0];
        if (type.equals("image")) {
            return true;
        }
        return false;
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }
}
