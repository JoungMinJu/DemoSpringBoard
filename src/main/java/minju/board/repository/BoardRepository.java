package minju.board.repository;

import minju.board.model.Board;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class BoardRepository {
    private static Map<Long, Board> map = new HashMap<>();
    private static long sequence = 0L;

    // 저장
    public Board save(Board board){
        board.setId(++sequence);
        map.put(board.getId(), board);
        return board;
    }
    // 하나 찾기
    public Board findById(long id){
        return map.get(id);
    }
    // 전체 찾기
    public List<Board> findAll(){
        return new ArrayList<>(map.values());
    }
    // 삭제하기
    public void delete(long id){
        map.remove(id);
    }
    // clear
    public void clear(){
        map.clear();
    }

}
