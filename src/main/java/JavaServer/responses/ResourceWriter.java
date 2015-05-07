package JavaServer.responses;

import JavaServer.helpers.RangeFinder;

import java.io.IOException;
import java.util.List;

public interface ResourceWriter {

    public boolean doesFileExist();

    public String getDirectoryLinks();

    public List<String> convertFilesToPaths();

    public List<String> getDirectoryFiles();

    public void getFileContents();

    public void getDataFileContents();

    public void getPartialFileContents(RangeFinder rangeFinder) throws IOException;

    public void patchFileWithNewData(String newData) throws IOException;

    public void setDataInResource(String paramData);

    public void deleteDataFromResource();
}
