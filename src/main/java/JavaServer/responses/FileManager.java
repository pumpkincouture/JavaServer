package JavaServer.responses;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
    private File filePath;
    private DataOutputStream out;

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
        readPartialBytesFromFile(byteRange);
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }

    private void readPartialBytesFromFile(Map<String, String> requestHeaders) throws IOException {
        if (getRange(requestHeaders).length() > 2) {
//            out.write(getPartialContentWithRangeStartAndFinish(requestHeaders));
            readBytesFromFile();
        }
        readBytesFromFile();
//        out.write(getPartialContentWithMissingBoundary(requestHeaders));
    }

    private byte[] getPartialContentWithMissingBoundary(Map<String, String> requestHeaders) throws IOException {
        Pattern method = Pattern.compile("-\\d+");
        Matcher matcher = method.matcher(getRange(requestHeaders));
        byte[] content;

        byte[] fileTotalBytes = Files.readAllBytes(filePath.toPath());

        if (matcher.find()) {
            content = getOuterBoundary(requestHeaders, fileTotalBytes);
        } else {
            content = getStartOfRange(requestHeaders, fileTotalBytes);
        }

        return content;
    }

    private byte[] getStartOfRange(Map<String, String> requestHeaders, byte[] fileBytes) {
        return Arrays.copyOfRange(fileBytes, findFirstBoundary(requestHeaders), fileBytes.length);
    }

    private byte[] getOuterBoundary(Map<String, String> requestHeaders, byte[] fileBytes) {
        return Arrays.copyOfRange(fileBytes, findFirstBoundary(requestHeaders, fileBytes), fileBytes.length);
    }

    private int findFirstBoundary(Map<String, String> requestHeaders, byte[] fileBytes) {
        return fileBytes.length + Integer.parseInt(getRange(requestHeaders).split("-")[0]);
    }

    private int findFirstBoundary(Map<String, String> requestHeaders) {
        return Integer.parseInt(getRange(requestHeaders).split("-")[0]);
    }

    private byte[] getPartialContentWithRangeStartAndFinish(Map<String, String> requestHeaders) throws IOException {
        String[] rangeBoundaries = getRange(requestHeaders).split("-");

        byte[] fileTotalBytes = Files.readAllBytes(filePath.toPath());

        return Arrays.copyOfRange(fileTotalBytes, Integer.parseInt(rangeBoundaries[0]), Integer.parseInt(rangeBoundaries[1]));
    }

    private String getRange(Map<String, String> requestHeaders) {
        System.out.println(requestHeaders.get("Range"));
        System.out.println(requestHeaders.get("Range").toString().split("=")[1]);
        return requestHeaders.get("Range").toString().split("=")[1];
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
}
