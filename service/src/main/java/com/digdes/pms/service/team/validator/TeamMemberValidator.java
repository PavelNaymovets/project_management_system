package com.digdes.pms.service.team.validator;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.service.util.validator.Validator;

public interface TeamMemberValidator extends Validator<TeamMemberDto> {
    @Override
    void validate(TeamMemberDto teamMemberDto);
}
