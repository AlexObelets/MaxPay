package Utils;

import java.util.List;

public class ResponseParser {

    public boolean isElementInArray(List<String> list, String value) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                flag = true;
                break;
            } else flag = false;
        }
        return flag;
    }
}
