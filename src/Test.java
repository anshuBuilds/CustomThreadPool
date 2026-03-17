public class Test {
    static void main() {
        SimpleExecutor ex = new SimpleExecutor();

        for(int i=0;i<100000;i++){
            int x = i;
            new Thread(() -> {
                ex.submit(() -> System.out.println(x));
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
