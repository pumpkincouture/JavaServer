package JavaServer.responses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public void patchFileWithNewData(String newData) throws IOException {
        byte[] b = newData.getBytes();

        try {
            Files.write(Paths.get("/Users/test/code/JavaServer/public/patch-content.txt"), b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToResource(Map<String, String> requestData, String equalitySign) {
         byte[] b = turnDataIntoString(requestData, equalitySign).getBytes();

        try {
            Files.write(Paths.get("/Users/test/code/JavaServer/data/dataFile"), b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        byte[] b = "".getBytes();

        try {
            Files.write(Paths.get("/Users/test/code/JavaServer/data/dataFile"), b);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void getDataFileContents() {
        this.readBytesFromFile(new File("/Users/test/code/JavaServer/data/dataFile"));
    }

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException {
        readBytesFromFile(rangeFinder);
    }

    private String turnDataIntoString(Map<String, String> updatedData, String typeOfEqualitySign) {
        String requestDataString = "";
        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            requestDataString += entry.getKey() + typeOfEqualitySign + entry.getValue();
        }
        return requestDataString;
    }

    public String turnDataIntoString(Map<String, String> patchData) {
        String patchDataString = "";
        for (Map.Entry<String, String> entry : patchData.entrySet()) {
            patchDataString += entry.getValue() + " ";
        }
        return patchDataString;
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
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readBytesFromFile(RangeFinder rangeFinder) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readBytesFromFile(File pathToData) {
        try {
            InputStream file = new FileInputStream(pathToData);
            byte[] bytes = Files.readAllBytes(pathToData.toPath());

            while (file.read(bytes) > 0) {
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getMainDirectory() {
        return filePath.getParentFile();
    }

    private int getFileLength() {
        return (int) filePath.length();
    }
}
