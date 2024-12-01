package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.ProfileRequest;
import org.example.ecommerce.domain.authentication.dto.responses.ProfileResponse;
import org.example.ecommerce.domain.authentication.entity.Profile;

public class ProfileMapper {
    // ProfileRequest -> Entity
    public static Profile toEntity(ProfileRequest profileRequest) {
        return Profile.builder()
                .dateOfBirth(profileRequest.getDateOfBirth())
                .gender(profileRequest.getGender())
                .interest(profileRequest.getInterest())
                .avatar(profileRequest.getAvatar())
                .build();
    }

    // Entity -> ProfileResponse
    public static ProfileResponse toResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .dateOfBirth(profile.getDateOfBirth())
                .gender(profile.getGender())
                .interest(profile.getInterest())
                .avatar(profile.getAvatar())
                .build();
    }
}
