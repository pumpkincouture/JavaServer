package JavaServer.helpers;

import java.io.File;

public class HTMLBuilder {
    private File filePath;

    public HTMLBuilder(File filePath) {
        this.filePath = filePath;
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
}
