package com.noterror.app.api.member.controller;

import com.noterror.app.api.member.dto.MemberIdResponseDto;
import com.noterror.app.api.member.dto.MemberResponseDto;
import com.noterror.app.api.member.dto.UpdateInfoDto;
import com.noterror.app.api.member.service.MemberService;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.response.SingleMemberResponse;
import com.noterror.app.api.member.dto.SignUpDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Api(tags = {"front office", "members"})
@RestController
@Validated
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 정보 등록
     */
    @ApiOperation(value = "일반 회원가입 API", notes = "회원가입 폼 데이터에 새로운 회원을 생성하여 DB에 저장합니다.")
    @ApiResponse(code = 201, message = "created")
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> postMember(@RequestBody @Valid SignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        Long newMemberId = memberService.saveMemberInfo(member);

        return new ResponseEntity(
                new MemberIdResponseDto(newMemberId), HttpStatus.CREATED);
    }

    /**
     * 채식 유형 등록
     */
    @ApiOperation(value = "채식주의자 유형 정보 저장 API", notes = "회원가입 후, 본인의 채식주의자 유형을 선택합니다.")
    @ApiResponse(code = 200, message = "ok")
    @PostMapping("/sign-up/type/{member-id}")
    public ResponseEntity<MemberResponseDto> postVegetarianTypeOfNewMember(
            @ApiParam(example = "1") @PathVariable("member-id") Long memberId,
            @ApiParam(value = "비건",example = "vegetarianType : 비건") @RequestBody HashMap<String, String> vegetarianType) {

        Member member = memberService.saveTypeOfNewMember(memberId, vegetarianType.get("vegetarianType"));
        MemberResponseDto response = new MemberResponseDto(member);

        return new ResponseEntity(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     */
    @ApiOperation(value = "회원 정보 수정 API", notes = "회원의 정보를 수정합니다.")
    @ApiResponse(code = 200, message = "ok")
    @PutMapping("/info")
    public ResponseEntity putMember(
            @ApiParam @RequestBody @Valid UpdateInfoDto updateInfoDto) {
        Member updateInfo = updateInfoDto.toEntityWithEmail(currentUserEmail());
        Member updateMember = memberService.updateMember(updateInfo);
        MemberResponseDto response = new MemberResponseDto(updateMember);

        return new ResponseEntity<>(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 개별 회원 정보 조회
     */
    @ApiOperation(value = "회원 정보 조회 API", notes = "회원의 정보를 조회합니다.")
    @ApiResponse(code = 200, message = "ok")
    @GetMapping("/info")
    public ResponseEntity getMember() {
        Member findMember = memberService.findMemberByEmail(currentUserEmail());
        MemberResponseDto response = new MemberResponseDto(findMember);
        return new ResponseEntity<>(
                new SingleMemberResponse(response), HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     */
    @ApiOperation(value = "회원 정보 삭제 API", notes = "회원의 정보를 삭제합니다.")
    @ApiResponse(code = 204, message = "No content")
    @DeleteMapping("/info")
    public ResponseEntity deleteProduct() {
        memberService.removeMember(currentUserEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String currentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}