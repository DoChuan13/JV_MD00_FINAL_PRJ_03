package app.config;

public final class DirectPage implements Runnable {
    private Thread thread;

    public void directPage() {
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(3000);
            }
        } catch (Exception errException) {
            errException.printStackTrace();
        }
    }
}
