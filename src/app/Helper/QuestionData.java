package app.Helper;

public class QuestionData {
    public String quesText;
    public String quesImage;
    public String[] answers;
    public String correctAnswer;

    public QuestionData(String quesText,String quesPicture, String[] answers, String correctAnswer) {
        this.quesText = quesText;
        this.quesImage = quesPicture;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
}

