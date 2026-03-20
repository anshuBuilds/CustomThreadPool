import executor.SimpleExecutor;

void main() {
    SimpleExecutor ex = new SimpleExecutor();

//    for(int j = 0; j < 10; j++) {
//        ex.submit(() -> {
//            long sum = 0;
//            for(int i = 0; i < 10000; i++) {
//                sum = sum + i;
//            }
//            System.out.println(Thread.currentThread().getName() + " " + sum);
//        });
//    }

    for(int j = 0; j < 1000; j++) {
        ex.submit(() -> {
            try {
                Thread.sleep(10); // slow down
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("task");
        });
    }

    ex.shutdown();

    ex.submit(() -> System.out.println("task"));

}
