package service;
import model.Role;
import model.User;

import java.util.Map;
import java.util.Optional;

public interface RoleService {
    // Назначение роли пользователю
    void assignRole(int userId, Role role);
    // Получение роли пользователя
    Optional<Role> getRole(int userId);
    // Получение всех ролей пользователей
    Map<Integer, Role> getAllRoles();
}