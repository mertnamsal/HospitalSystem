package com.mert.repository;

import com.mert.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

}
