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
	
	public void WriteDoc(Exam_DTO exam) {
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
        
        List<String> questions = Arrays.asList(
                "Câu 1: Java là gì?",
                "Câu 2: Từ khóa nào để khai báo một class trong Java?",
                "Câu 3: Kiểu dữ liệu nào dùng để lưu số thực?",
                "Câu 4: Vòng lặp nào trong Java?"
        );

        List<List<String>> answers = Arrays.asList(
                Arrays.asList("A. Một loại cà phê", "B. Một ngôn ngữ lập trình", "C. Một hệ điều hành", "D. Một trình duyệt"),
                Arrays.asList("A. define", "B. class", "C. struct", "D. object"),
                Arrays.asList("A. int", "B. double", "C. boolean", "D. char"),
                Arrays.asList("A. for", "B. loop", "C. repeat", "D. switch")
        );

       
        for (int i = 0; i < questions.size(); i++) {
            XWPFParagraph questionParagraph = document.createParagraph();
            XWPFRun questionRun = questionParagraph.createRun();
            questionRun.setText(questions.get(i));
            questionRun.setBold(true);

            
            for (String ans : answers.get(i)) {
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
