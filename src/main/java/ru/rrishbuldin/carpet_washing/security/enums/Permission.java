package ru.rrishbuldin.carpet_washing.security.enums;


/**
 * Enum со всеми возможными правами для ролей
 */
public enum Permission {
    /// Может видеть заказы, которые нужно забрать
    VIEW_ACTUAL_ORDERS,
    /// Может видеть все заказы
    VIEW_ALL_ORDERS,
    /// Может создавать заказы
    CREATE_ORDER,
    /// Может менять заказы(статус, комментарии и тд)
    EDIT_ORDER,
    /// Может назначать задачи
    ASSIGN_TASKS,
    /// Может управлять пользователями
    MANAGE_USERS
}
