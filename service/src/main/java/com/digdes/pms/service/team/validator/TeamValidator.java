package com.digdes.pms.service.team.validator;

import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.service.util.validator.Validator;

public interface TeamValidator extends Validator<TeamDto> {
    @Override
    void validate(TeamDto teamDto);
}
