package study.jpatransactional.pessimisticlock.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoardCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long boardId;

    private long count;

    public BoardCount(Long boardId) {
        this.boardId = boardId;
    }

    public void countUp(){
        this.count++;
    }
}
