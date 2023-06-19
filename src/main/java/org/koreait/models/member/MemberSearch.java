package org.koreait.models.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberSearch {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eDate;

    @NotBlank
    private String userNm;

    @NotBlank
    private String mobile;

    private Role role;

    private int page = 1;
    private int noOfRow;

    private String sopt;
    private String skey;

}