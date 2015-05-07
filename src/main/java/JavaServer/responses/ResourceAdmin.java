package JavaServer.responses;

import JavaServer.helpers.RangeFinder;

import java.io.IOException;
import java.util.List;

public interface ResourceAdmin {

    public boolean doesFileExist();

    public String getDirectoryLinks();

    public List<String> convertFilesToPaths();

    public List<String> getDirectoryFiles();

    public void getFileContents() throws IOException;

    public void getDataFileContents() throws IOException;

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException;

    public void patchFileWithNewData(String newData) throws IOException;

    public void setDataInResource(String paramData) throws IOException;

    public void deleteDataFromResource() throws IOException;
}
