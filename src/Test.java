void main() {
    SimpleExecutor ex = new SimpleExecutor();

//    for (int i = 0; i < 100; i++) {
//        int x = i;
//        new Thread(() -> {
//            ex.submit(() -> IO.println(x));
//        }).start();
//    }

    for(int j = 0; j < 10; j++) {
        ex.submit(() -> {
            long sum = 0;
            for(int i = 0; i < 10000; i++) {
                sum = sum + i;
            }
            System.out.println(Thread.currentThread().getName() + " " + sum);
        });
    }


//    ex.shutdown();

    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
