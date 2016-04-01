import java.io.Serializable;

public class TestClass implements Serializable {

    public static final int serialVersionUID = 1;

    public int testFunc() {
        int x = 0;
        return x++;
    }

    public int TESTFunc() {
        return 0;
    }

    public static void main(String[] args) {
        new TestClass().run();
    }

    public void run() {
        System.out.println(testFunc());
    }

};