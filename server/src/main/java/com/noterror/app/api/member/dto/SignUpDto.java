package com.noterror.app.api.member.dto;

import com.noterror.app.api.entity.member.Address;
import com.noterror.app.api.entity.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    @ApiModelProperty(value = "email@email.com", notes = "이메일", required = true)
    @NotEmpty(message = "이메일(ID)을 공백없이 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    /**
     * 회원 이름
     */
    @ApiModelProperty(value = "회원 이름", notes = "회원 이름", required = true)
    @NotEmpty(message = "이름을 공백없이 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{2,5}$")
    private String memberName;

    @ApiModelProperty(value = "패스워드", notes = "패스워드", required = true)
    @Nullable
    @Length(min = 8, max = 16, message = "비밀번호는 8글자 이상 16 글자 이하로 작성해야 합니다.")
    private String password;

    @ApiModelProperty(value = "전화번호", notes = "전화번호", required = true)
    @NotEmpty(message = "전화번호를 공백없이 입력해주세요.")
    @Pattern(regexp = "^010\\d{3,4}\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자를 구분자(-) 없이 입력해주세요.")
    private String phone;

    /**
     * 우편번호
     */
    @ApiModelProperty(value = "63331", notes = "우편번호", required = true)
    @NotEmpty
    private String zipCode;

    /**
     * 도로명 주소
     */
    @ApiModelProperty(value = "제주시", notes = "도시명", required = true)
    @NotEmpty
    private String city;

    /**
     * 상세 주소(사용자 입력)
     */
    @ApiModelProperty(value = "추가 상세 주소", notes = "추가 상세 주소")
    private String detailAddress;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .memberName(this.memberName)
                .password(this.password)
                .phone(this.phone)
                .address(new Address(
                        this.zipCode,
                        this.city,
                        this.detailAddress)
                )
                .build();
    }
}
