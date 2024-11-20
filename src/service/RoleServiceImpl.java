package service;

import model.Role;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    // Хранилище ролей
    private final Map<Integer, Role> roleStorage = new HashMap<>();

    public RoleServiceImpl(Map<Integer, Role> initialRoles) {
        if (initialRoles != null) {
            // Инициализация с заранее заданными ролями
            this.roleStorage.putAll(initialRoles);
        }
    }

    @Override
    public void assignRole(int userId, Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role yt vj;tm ,snm null");
        }
        // Назначение или обновление роли
        roleStorage.put(userId, role);
    }

    @Override
    public Optional<Role> getRole(int userId) {
        // Получаем роль по ID
        return Optional.ofNullable(roleStorage.get(userId));
    }

    @Override
    public Map<Integer, Role> getAllRoles() {
        // Возвращаем копию карты ролей
        return new HashMap<>(roleStorage);
    }
}