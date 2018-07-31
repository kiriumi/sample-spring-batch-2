package sample.spring.batch.file.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import sample.spring.batch.file.dto.Member;

public class MemberFieldSetMapper implements FieldSetMapper<Member> {

    @Override
    public Member mapFieldSet(final FieldSet fieldSet) throws BindException {
        Member member = new Member();

        member.setNum(fieldSet.readInt("num", 0));
        member.setName(fieldSet.readString("name"));

        return member;
    }

}
