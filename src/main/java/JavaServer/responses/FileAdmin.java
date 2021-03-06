package JavaServer.responses;

import JavaServer.helpers.HTMLBuilder;
import JavaServer.helpers.RangeFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileAdmin implements ResourceAdmin {
    private File filePath;
    private DataOutputStream out;

    public FileAdmin(File filePath, DataOutputStream out) {
        this.filePath = filePath;
        this.out = out;
    }

    public boolean doesFileExist() {
        return filePath.exists();
    }

    public String getDirectoryLinks() {
       return new HTMLBuilder(filePath).getDirectoryLinks();
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

    public void getFileContents() throws IOException {
        readBytesFromFile();
    }

    public void getDataFileContents() throws IOException {
        readBytesFromFile(new File("./data/dataFile"));
    }

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException {
        readBytesFromFile(rangeFinder);
    }

    public void patchFileWithNewData(String newData) throws IOException {
        byte[] dataBytes = newData.getBytes();
        writeToResource(dataBytes, filePath.toString());
    }

    public void setDataInResource(String paramData) throws IOException {
        byte[] dataBytes = paramData.getBytes();
        writeToResource(dataBytes, "./data/dataFile");
    }

    public void deleteDataFromResource() throws IOException {
        byte[] dataBytes = "".getBytes();
        writeToResource(dataBytes, "./data/dataFile");
    }

    private void writeToResource(byte[] dataBytes, String resourcePath) throws IOException {
        Files.write(Paths.get(resourcePath), dataBytes);
    }

    private void readBytesFromFile() throws IOException {
        InputStream file = new FileInputStream(filePath.toString());
        byte[] bytes = Files.readAllBytes(filePath.toPath());

        while (file.read(bytes) > 0) {
            out.write(bytes);
        }
    }

    private void readBytesFromFile(RangeFinder rangeFinder) throws IOException {
        InputStream file = new FileInputStream(filePath.toString());
        byte[] bytes = Files.readAllBytes(filePath.toPath());

        while (file.read(bytes) > 0) {
            if (!rangeFinder.hasStart()) {
                out.write(bytes, (getFileLength() - rangeFinder.getEnd()), rangeFinder.getEnd());
            } else if (!rangeFinder.hasEnd()) {
                Integer start = rangeFinder.getStart();
                out.write(bytes, start, getFileLength() - start);
            } else {
                out.write(bytes, rangeFinder.getStart(), rangeFinder.getEnd() + 1);
            }
        }
    }

    private void readBytesFromFile(File pathToData) throws IOException {
        InputStream file = new FileInputStream(pathToData);
        byte[] bytes = Files.readAllBytes(pathToData.toPath());

        while (file.read(bytes) > 0) {
            out.write(bytes);
        }
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }

    private int getFileLength() {
        return (int) filePath.length();
    }
}
