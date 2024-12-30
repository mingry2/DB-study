package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV101", 10000);
        Member savedMember = repository.save(member);
        assertThat(savedMember.getMemberId()).isEqualTo("memberV101");

        //findById
        Member findMember1 = repository.findById(member.getMemberId());
        assertThat(findMember1).isEqualTo(member);

        //update
        repository.update(member.getMemberId(), 20000);
        Member findMember2 = repository.findById(member.getMemberId());
        assertThat(findMember2.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

    }

}