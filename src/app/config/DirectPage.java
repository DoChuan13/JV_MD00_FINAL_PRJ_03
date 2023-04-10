package app.config;

public final class DirectPage implements Runnable {
    private static Thread thread = null;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Direct Page in " + i + " s");
                Thread.sleep(1000);
            }
        } catch (Exception errException) {
            errException.printStackTrace();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();
    }
}
