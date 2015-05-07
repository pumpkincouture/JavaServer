package JavaServer.responses;

import JavaServer.helpers.RangeFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriterMock implements ResourceWriter {
    private boolean displayDoesFileExist = false;
    private boolean displayGetDirectoryLinks = false;
    private boolean displayConvertFilesToPaths = false;
    private boolean displayPatchFileWithNewData = false;

    public boolean doesFileExist() {
        displayDoesFileExist = true;
        return displayDoesFileExist;
    }

    public String getDirectoryLinks() {
        displayGetDirectoryLinks = true;
        return "";
    }

    public List<String> convertFilesToPaths() {
        displayConvertFilesToPaths = true;
        List<String> mockList = new ArrayList<>();
        return mockList;
    }

    public List<String> getDirectoryFiles() {
        return null;
    }

    public void getFileContents() {

    }

    public void getDataFileContents() {

    }

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException {

    }

    public void patchFileWithNewData(String newData) throws IOException {
        displayPatchFileWithNewData = true;
    }

    public void setDataInResource(String paramData) {

    }

    public void deleteDataFromResource() {
    }

    public boolean isPatchFileWithNewDataMessageCalled() {
        return displayPatchFileWithNewData;
    }
}
