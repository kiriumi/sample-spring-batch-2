package sample.spring.batch.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sample.spring.batch.file.dto.Member;

public class ExampleUtils {

    @Autowired
    @Qualifier("sampleMember")
    private Member member;

    public void setMemberName(final String name) {
        member.setName(name);
    }

    public String getMemberName() {
        return member.getName();
    }

}
