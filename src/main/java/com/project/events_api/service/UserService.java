package com.project.events_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events_api.dto.UserDTO;
import com.project.events_api.mapper.UserMapper;
import com.project.events_api.model.User;
import com.project.events_api.repository.UserRepository;
import com.project.events_api.util.ResourceNotFoundException;

/**
 * Classe de serviço para a entidade User
 * CRUD de usuários
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Cria um novo usuário
     * 
     * @param userDTO - DTO do usuário
     * @return DTO do usuário criado
     */
    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toModel(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    /**
     * Retorna todos os usuários
     * 
     * @return Lista de DTOs de usuários
     */
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    /**
     * Retorna um usuário por ID
     * 
     * @param id - ID do usuário
     * @return DTO do usuário
     */
    public UserDTO findById(Long id) {
        User user = validateExistsUser(id);
        return userMapper.toDTO(user);
    }

    /**
     * Atualiza um usuário existente
     * 
     * @param id      - ID do usuário a ser atualizado
     * @param userDTO - DTO com os novos dados do usuário
     * @return DTO atualizado do usuário
     */
    public UserDTO update(Long id, UserDTO userDTO) {
        User oldUser = validateExistsUser(id);
        User newUser = userMapper.toModel(userDTO);

        oldUser = helperUpdate(oldUser, newUser);

        return userMapper.toDTO(userRepository.save(oldUser));
    }

    /**
     * Deleta um usuário por ID
     * 
     * @param id - ID do usuário a ser deletado
     */
    public void delete(Long id) {
        validateExistsUser(id);
        userRepository.deleteById(id);
    }

    /**
     * Valida se o usuário existe pelo ID
     * 
     * @param id - ID do usuário a ser verificado
     * @return Usuário encontrado
     */
    private User validateExistsUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    /**
     * Atualiza os dados de um usuário
     * 
     * @param oldUser - Usuário a ser atualizado
     * @param newUser - Usuário com os novos dados
     * @return Usuário atualizado
     */
    private User helperUpdate(User oldUser, User newUser) {
        oldUser.setPerson(newUser.getPerson());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setUsername(newUser.getUsername());
        return oldUser;
    }
}
