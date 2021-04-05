package com.inflearn.jpaactive2.controller;

import com.inflearn.jpaactive2.domain.Member;
import com.inflearn.jpaactive2.dto.member.*;
import com.inflearn.jpaactive2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public ResultResponse<List<MemberDTO>> membersV2() {
        List<Member> members = memberService.findMembers();
        List<MemberDTO> memberDTOs = members.stream()
                .map(m -> new MemberDTO(m.getName()))
                .collect(Collectors.toList());

        return new ResultResponse<>(memberDTOs);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(
            @Valid @RequestBody CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getUsername());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV1(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateMemberRequest request) {

        memberService.update(id, request.getUsername());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
}
