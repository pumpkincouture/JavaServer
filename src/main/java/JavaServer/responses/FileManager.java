package JavaServer.responses;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FileManager {
    private File filePath;
    private DataOutputStream out;
    private String start;
    private String end;

    public FileManager(File filePath, DataOutputStream out) {
        this.filePath = filePath;
        this.out = out;
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

    public void patchFile(String newData) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
        bw.write(newData);
        bw.close();
    }

    public List<String> getDirectoryFiles() {
        List<String> directoryFiles;

        String[] filesList = getMainDirectory().list();

        directoryFiles = Arrays.asList(filesList);

        return directoryFiles;
    }

    public void getFileContents()  {
        readBytesFromFile();
    }

    public void getPartialFileContents(Map<String, String> byteRange) throws IOException {
        readBytesFromFile(byteRange);
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }

    private void getRange(Map<String, String> requestHeaders) {
        String header = requestHeaders.get("Range");
        String[] rangeValues = header.split("=");
        String[] values = rangeValues[1].split("-");
        start = values[0].trim();
        end = values[1].trim();
    }

    public Integer getStart() {
        return Integer.parseInt(start);
    }

    public Integer getEnd() {
        return Integer.parseInt(end);
    }

    public boolean hasStart() {
        return !start.isEmpty();
    }

    public boolean hasEnd() {
        return !end.isEmpty();
    }

    private void readBytesFromFile() {
        try {
            InputStream file = new FileInputStream(filePath.toString());
            byte[] bytes = Files.readAllBytes(filePath.toPath());

            while (file.read(bytes) > 0) {
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readBytesFromFile(Map<String, String> requestHeaders) {
        getRange(requestHeaders);
        try {
            InputStream file = new FileInputStream(filePath.toString());
            byte[] bytes = Files.readAllBytes(filePath.toPath());

            while (file.read(bytes) > 0) {
                if (!hasStart()) {
                    out.write(bytes, (getFileLength() - getEnd()), getEnd());
                } else if (!hasEnd()) {
                    Integer start = getStart();
                    out.write(bytes, start, getFileLength() - start);
                } else {
                    out.write(bytes, getStart(), getEnd() + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getFileLength() {
        return (int) filePath.length();
    }
}
