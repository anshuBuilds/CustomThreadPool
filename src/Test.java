void main() {
    SimpleExecutor ex = new SimpleExecutor();

    for (int i = 0; i < 100; i++) {
        int x = i;
        new Thread(() -> {
            ex.submit(() -> IO.println(x));
        }).start();
    }

    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
