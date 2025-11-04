package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.mapper.ProfileUserMapper;
import chinanko.chinanko.repository.ProfileUserRepository;

@Service
public class ProfileUserServiceImpl implements ProfileUserService{

    private final ProfileUserRepository repository;
    private final ProfileUserMapper mapper;

    public ProfileUserServiceImpl(ProfileUserRepository repository, ProfileUserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProfileUserResponse> listAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
