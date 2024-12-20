package hcmute.service.impl;

import hcmute.entity.UserEntity;
import hcmute.entity.UserRoleEntity;
import hcmute.repository.UserRepository;
import hcmute.repository.UserRoleRepository;
import hcmute.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserRoleServiceImpl implements IUserRoleService {
    UserRoleRepository userRoleRepo;
    UserRepository userRepo;

    public List<UserRoleEntity> findRolesOfAdministrators() {
        List<UserEntity> accounts = userRepo.getAdministrators();
        return userRoleRepo.authoritiesOf(accounts);
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRoleEntity create(UserRoleEntity auth) {
        return userRoleRepo.save(auth);
    }

    @Override
    public Optional<UserRoleEntity> findById(Integer id) {
        return userRoleRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        userRoleRepo.deleteById(id);
    }

    @Override
    public int countUser() {
        return userRoleRepo.countUser();
    }

    @Override
    public List<UserRoleEntity> findByRoleId(String id) {
        return userRoleRepo.findByRoleId(id);
    }

    @Override
    public <S extends UserRoleEntity> S save(S entity) {
        return userRoleRepo.save(entity);
    }
}
