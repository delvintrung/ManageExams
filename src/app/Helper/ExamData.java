package app.Helper;

public class ExamData {
    public String[] questions;
    public String[] images;
    public String[][] options;
    public String[] correctAnswers;

    public ExamData(String[] questions,String[] images,String[][]  options, String[] correctAnswers) {
        this.questions = questions;
        this.images = images;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }
}

