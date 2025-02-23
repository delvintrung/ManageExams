package app.Helper;

public class QuestionData {
    public String quesText;
    public String[] answers;
    public String correctAnswer;

    public QuestionData(String quesText, String[] answers, String correctAnswer) {
        this.quesText = quesText;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
}

