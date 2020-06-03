package affirm;

public class CompressString {
    public CompressString() {
    }

    public static void main(String[] args) {
        String s = "abb1ccc2222";
        CompressString cs = new CompressString();
        System.out.println(cs.compressWithNumber(s));
    }

    public String compress(String s) {
        if (s == null || s.length() <= 1)
            return s;
        StringBuilder str = new StringBuilder();
        int count = 1;
        for (int right = 1; right <= s.length(); right++) {
            if (right < s.length() && s.charAt(right) == s.charAt(right - 1)) {
                count++;
                continue;
            }
            str.append(s.charAt(right - 1));
            if (count == 1) {
                continue;
            }
            String countStr = String.valueOf(count);
            str.append(countStr);
            count = 1;
        }
        return str.toString();
    }

    public String decompress(String s) {
        if (s == null || s.length() <= 1)
            return s;
        StringBuilder str = new StringBuilder();
        int count = 0;
        char pre = s.charAt(0);
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                count = count * 10 + s.charAt(i) - '0';
            } else {
                str.append(pre);
                while (count > 1) {
                    str.append(pre);
                    count--;
                }
                if (i < s.length()) {
                    pre = s.charAt(i);
                    count = 0;
                }
            }
        }
        return str.toString();
    }

    public String decompressWithNumberStartsSlash(String s) {
        if (s == null || s.length() <= 1)
            return s;
        StringBuilder str = new StringBuilder();
        int count = 0;
        char pre = s.charAt(0);
        boolean numFlag = s.charAt(0) == '/' ? true : false;
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                if (numFlag) {
                    pre = s.charAt(i);
                    numFlag = false;
                }
                if (!numFlag && Character.isDigit(pre)){
                    count = s.charAt(i) - '0';
                } else {
                    count = count * 10 + s.charAt(i) - '0';
                }
            } else {
                if (i < s.length() && s.charAt(i) == '/') {
                    numFlag = true;
                }
                str.append(pre);
                while (count > 1) {
                    str.append(pre);
                    count--;
                }
                if (i < s.length()) {
                    pre = s.charAt(i);
                    count = 0;
                }
            }
        }
        return str.toString();
    }

    public String decompressWithNumber(String s) {
        if (s == null || s.length() <= 1)
            return s;
        StringBuilder str = new StringBuilder();
        int count = 0;
        char pre = s.charAt(0);

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                if (count == 0){
                    count = s.charAt(i) - '0';
                } else {
                    str.append(pre);
                    while (count > 1) {
                        str.append(pre);
                        count--;
                    }
                    pre = s.charAt(i);
                    count = 0;
                }
            } else {
                str.append(pre);
                while (count > 1) {
                    str.append(pre);
                    count--;
                }
                if (i < s.length()) {
                    pre = s.charAt(i);
                    count = 0;
                }
            }
        }
        return str.toString();
    }

    public String compressWithNumber(String s) {
        if (s == null || s.length() <= 1)
            return s;
        StringBuilder str = new StringBuilder();
        int count = 1;
        for (int right = 1; right <= s.length(); right++) {
            if (right < s.length() && s.charAt(right) == s.charAt(right - 1)) {
                count++;
                continue;
            }
            str.append(s.charAt(right - 1));
            if (count == 1) {
                continue;
            }
            String countStr = String.valueOf(count);
            str.append(countStr);
            count = 1;
        }
        return str.toString();
    }
}
