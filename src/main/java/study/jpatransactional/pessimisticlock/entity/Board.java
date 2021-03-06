package study.jpatransactional.pessimisticlock.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private long boardCount ;

    @Version
    Integer version;

    public Board(String title) {
        this.title = title;
    }

    public void countUpdate() {
        this.boardCount++;
    }
}
