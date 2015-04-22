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

    public String getDirectoryLinks() {
        String[] fileList = filePath.list();

        String link = "";

        link += "<html>" + "\r\n";
        link += "<body>" + "\r\n";

        for (String line : fileList) {
            link += "<a href='/"+ line + "'" + ">" + line + "</a>" + "\r\n";
        }

        link += "</body>" + "\r\n";
        link += "</html";
        return link;
    }

    public void getFileContents() {


    }
}
