//package minju.board;
//
//import lombok.RequiredArgsConstructor;
//import minju.board.model.Board;
//import minju.board.repository.ArticleRepository;
//import minju.board.repository.BoardRepository;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//@RequiredArgsConstructor
//public class TesDataInit {
//    private final BoardRepository boardRepository;
//
//
//    // 게시판 미리 추가
//    @PostConstruct
//    public void init(){
//        boardRepository.save(new Board("자유게시판", "자유게시판"));
//        boardRepository.save(new Board("정보게시판", "정보게시판"));
//    }
//}
