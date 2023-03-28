package com.mert.service;

import com.mert.dto.request.UserSaveRequestDto;
import com.mert.dto.response.UserFindAllResponseDto;
import com.mert.mapper.IUserProfileMapper;
import com.mert.rabbitmq.model.CreateUser;
import com.mert.repository.IUserProfileRepository;
import com.mert.repository.entity.UserProfile;
import com.mert.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public void save(CreateUser createUser) {
        UserProfile userProfile = UserProfile.builder()
                .email(createUser.getEmail())
                .authid(createUser.getAuthid())
                .username(createUser.getUsername())
                .date(createUser.getDate())
                .age(Integer.toString(Period.between(createUser.getDate(),LocalDate.now()).getYears()))
                .build();
        save(userProfile);
    }


    public List<UserFindAllResponseDto> findAllDto() {
        List<UserProfile> userProfileList = findAll();
        return userProfileList.stream().map(x->IUserProfileMapper.INSTANCE.toUserFindAllResponseDto(x)).collect(Collectors.toList());

    }
}
