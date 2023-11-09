public class StringBuffer {
    public static void main(String[] args) {
        // Creating a MyStringBuffer
        MyStringBuffer stringBuffer = new MyStringBuffer("Halamithi");

        // 1. append()
        stringBuffer.append(" Habibo");
        System.out.println("1. append(): " + stringBuffer);

        // 2. insert()
        stringBuffer.insert(5, " Java");
        System.out.println("2. insert(): " + stringBuffer);

        // 3. replace()
        stringBuffer.replace(6, 11, "Universe");
        System.out.println("3. replace(): " + stringBuffer);

        // 4. delete()
        stringBuffer.delete(0, 6);
        System.out.println("4. delete(): " + stringBuffer);

        // 5. charAt()
        char charAtResult = stringBuffer.charAt(2);
        System.out.println("5. charAt(): " + charAtResult);

        // 6. setCharAt()
        stringBuffer.setCharAt(0, 'V');
        System.out.println("6. setCharAt(): " + stringBuffer);

        // 7. length()
        int lengthResult = stringBuffer.length();
        System.out.println("7. length(): " + lengthResult);

        // 8. capacity()
        int capacityResult = stringBuffer.capacity();
        System.out.println("8. capacity(): " + capacityResult);

        // 9. ensureCapacity()
        stringBuffer.ensureCapacity(20);
        System.out.println("9. ensureCapacity(): " + stringBuffer);

        // 10. toString()
        String toStringResult = stringBuffer.toString();
        System.out.println("10. toString(): " + toStringResult);

        // 11. substring(int start)
        String substringResult1 = stringBuffer.substring(2);
        System.out.println("11. substring(int start): " + substringResult1);

        // 12. substring(int start, int end)
        String substringResult2 = stringBuffer.substring(2, 6);
        System.out.println("12. substring(int start, int end): " + substringResult2);
    }
}

class MyStringBuffer {
    private StringBuilder buffer;

    public MyStringBuffer(String initial) {
        this.buffer = new StringBuilder(initial);
    }

    public void append(String str) {
        buffer.append(str);
    }

    public void insert(int index, String str) {
        buffer.insert(index, str);
    }

    public void replace(int start, int end, String str) {
        buffer.replace(start, end, str);
    }

    public void delete(int start, int end) {
        buffer.delete(start, end);
    }

    public char charAt(int index) {
        return buffer.charAt(index);
    }

    public void setCharAt(int index, char ch) {
        buffer.setCharAt(index, ch);
    }

    public int length() {
        return buffer.length();
    }

    public int capacity() {
        return buffer.capacity();
    }

    public void ensureCapacity(int minCapacity) {
        buffer.ensureCapacity(minCapacity);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public String substring(int start) {
        return buffer.substring(start);
    }

    public String substring(int start, int end) {
        return buffer.substring(start, end);
    }
}
