package study.jpatransactional.pessimisticlock.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jpatransactional.pessimisticlock.entity.Board;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestBoard {

    private String title;


    public Board toEntity() {
        return new Board(this.title);
    }
}
