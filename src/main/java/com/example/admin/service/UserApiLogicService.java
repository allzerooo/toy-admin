package com.example.admin.service;

import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.entity.User;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.UserApiRequest;
import com.example.admin.model.network.response.UserApiResponse;
import com.example.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. get request data
        UserApiRequest userApiRequest = request.getData();

        // 2. create user
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        return response(newUser);

    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById 등으로 검색
        Optional<User> optionalUser = userRepository.findById(id);

        // user -> userApiResponse return
        return optionalUser
                .map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> optionalUser = userRepository.findById(userApiRequest.getId());

        return optionalUser.map(user -> {
            // 3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setStatus(userApiRequest.getStatus())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;
        })
        .map(user -> userRepository.save(user)) // 업데이터트된 유저 객체가 반환
        .map(updateUser -> response(updateUser))
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }


    @Override
    public Header delete(Long id) {
        return null;
    }

    // 여러 메서드에서 공통으로 사용되는 Response Object return method
    // user object -> create userApiResponse -> return
    private Header<UserApiResponse> response(User user){

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);

    }

}
