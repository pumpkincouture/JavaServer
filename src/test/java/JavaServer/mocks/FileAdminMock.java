package JavaServer.mocks;

import JavaServer.helpers.RangeFinder;
import JavaServer.responses.ResourceAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAdminMock implements ResourceAdmin {
    private boolean displayDoesFileExist = false;
    private boolean displayGetDirectoryLinks = false;
    private boolean displayConvertFilesToPaths = false;
    private boolean displayPatchFileWithNewData = false;
    private boolean displayFileContentsCalled = false;
    private boolean displayGetDataFileContentsCalled = false;
    private boolean getPartialFileContentsCalled = false;
    private boolean setDataInResourceCalled = false;
    private boolean deleteDataFromResourceCalled = false;

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
        displayFileContentsCalled = true;
    }

    public void getDataFileContents() {
        displayGetDataFileContentsCalled = true;
    }

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException {
        getPartialFileContentsCalled = true;
    }

    public void patchFileWithNewData(String newData) throws IOException {
        displayPatchFileWithNewData = true;
    }

    public void setDataInResource(String paramData) {
        setDataInResourceCalled = true;
    }

    public void deleteDataFromResource() {
        deleteDataFromResourceCalled = true;
    }

    public boolean isPatchFileWithNewDataMessageCalled() {
        return displayPatchFileWithNewData;
    }
}
