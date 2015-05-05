package JavaServer.helpers;

import java.util.Map;

public class RangeFinder {
    private Map<String, String> rangeParams;
    private String start;
    private String end;

    public RangeFinder(Map<String, String> rangeParams) {
        this.rangeParams = rangeParams;
        getRange();
    }

    public void getRange() {
        String header = rangeParams.get("Range");
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
}
