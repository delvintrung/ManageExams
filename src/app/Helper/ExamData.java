package app.Helper;

public class ExamData {
    public String[] questions;
    public String[][] options;
    public String[] correctAnswers;

    public ExamData(String[] questions, String[][] options, String[] correctAnswers) {
        this.questions = questions;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }
}

