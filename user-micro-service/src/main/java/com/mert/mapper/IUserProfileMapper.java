package com.mert.mapper;

import com.mert.dto.request.UserSaveRequestDto;
import com.mert.dto.response.UserFindAllResponseDto;
import com.mert.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    UserProfile toUserProfile(final UserSaveRequestDto dto);

    UserFindAllResponseDto toUserFindAllResponseDto(final UserProfile userProfile);
}
