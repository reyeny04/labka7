class ExamChecking implements Runnable {
    public static final int TOTAL_SHEETS = 500;
    public static final int SHEETS_PER_THREAD = 50;
    public static final int ITERATION_LIMIT = 6;

    private int startSheet;
    private int endSheet;

    public ExamChecking(int threadNumber) {
        this.startSheet = threadNumber * SHEETS_PER_THREAD;
        this.endSheet = this.startSheet + SHEETS_PER_THREAD;
    }

    @Override
    public void run() {
        for (int i = 1; i <= ITERATION_LIMIT; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() +
                    " is checking sheets from " + startSheet + " to " + endSheet +
                    " - Iteration " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}