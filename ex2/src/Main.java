public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < ExamChecking.TOTAL_SHEETS / ExamChecking.SHEETS_PER_THREAD; i++) {
            Thread thread = new Thread(new ExamChecking(i));
            thread.start();
        }
    }
}