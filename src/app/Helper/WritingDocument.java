package app.Helper;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import app.DTO.Exam_DTO;

public class WritingDocument {
	
	public void WriteDoc(ExamData exam) {
		try {
		XWPFDocument document = new XWPFDocument();

        
		XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("ĐỀ THI MÔN JAVA CƠ BẢN");
        titleRun.setBold(true);
        titleRun.setFontSize(16);
        
        
        XWPFParagraph info = document.createParagraph();
        XWPFRun infoRun = info.createRun();
        infoRun.setText("Thời gian làm bài: 60 phút");
        infoRun.addBreak();
        infoRun.setText("Họ và tên: ___________________   Lớp: _________");
        infoRun.addBreak();
        
        String[] questions = exam.questions;

        String[][] answers = exam.options;

       
        for (int i = 0; i < questions.length; i++) {
            XWPFParagraph questionParagraph = document.createParagraph();
            XWPFRun questionRun = questionParagraph.createRun();
            questionRun.setText(questions[i]);
            questionRun.setBold(true);

            
            for (String ans : answers[i]) {
                XWPFParagraph answerParagraph = document.createParagraph();
                XWPFRun answerRun = answerParagraph.createRun();
                answerRun.setText(ans);
            }
        }

        
        FileOutputStream out = new FileOutputStream("Exam.docx");
        document.write(out);
        out.close();
        document.close();

        System.out.println("✅ File DOCX đã được tạo thành công!");
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
	
}
