package org.khasanof.enums.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    WELCOME_TO("Welcome to", "Добро пожаловать", "Xush kelibsiz"),
    ADD("Add","Добавлять","Qo'shish"),
    DELETE("Delete", "Удалить", "Oʻchirish"),
    CANCEL("Cancel","Отмена","Bekor qilish"),
    NAME("Name", "Имя", "Ism"),
    PRICE("Price","Цена","Narxi"),
    COUNT("Count", "Считать", "Hisoblash"),
    RESULT("Result", "Результат", "Natija"),
    DESC("Description", "Описание", "Tavsif"),
    BACK_MENU("Back Menu", "Назад Меню", "Orqaga menyu"),
    SUCCESS("Successfully", "Успешно", "Muvaffaqiyatli"),
    PHONE("Update phone number", "Обновить номер телефона", "Telefon raqamini yangilash"),
    CARD("Update cards", "Обновить карты", "Kartalarni yangilash"),
    CHANGE_LANGUAGE("Change language", "изменение языка", "Tilni o'zgartirish"),
    CHANGE("Change", "Изменять", "O'zgartirish"),
    CHOOSE_MENU("Choose one of the menu", "Выберите одно из меню", "Menyulardan birini tanlang"),
    LOGIN("Login", "логин", "Kirish"),
    TO_ORDER("To order", "Заказать", "Buyurtma qilish"),
    MY_ORDER("My orders", "Мои заказы", "Mening buyurtmalarim"),
    BASKET("Basket", "Корзина", "Savat"),
    SUCCESSFULLY_BUY_PRODUCT("Successfully buy product", "Успешно купить товар", "Mahsulotni muvaffaqiyatli sotib oling"),
    BUY("Buy", "Купить", "Sotib olish"),
    MENU("Menu", "Меню", "Menyu"),
    PRODUCTS("Products", "Продукты", "Mahsulotlar"),
    SETTINGS("Settings", "Настройки", "Sozlamalar"),
    DISCOUNT("Discount", "Скидка", "Chegirma"),
    REGISTER("Register", "регистр", "Ro'yxatdan o'tish"),
    BACK("Back", "Назад", "Orqaga"),
    ENTER_FIRSTNAME("Enter your firstname", "Введите свое имя", "Ismingizni kiriting"),
    ENTER_LASTNAME("Enter your lastname", "Введите вашу фамилию", "Familiyangizni kiriting"),
    ENTER_NAME("Enter your username", "Введите ваше username", "usernamingizni kiriting"),
    ENTER_PASSWORD("Enter your password", "Введите свой пароль", "Parolingizni kiriting"),
    CHOOSE_LANGUAGES("Choose one language", "Выберите один из языков", "Tillardan birini tanlang");
    private final String en;
    private final String ru;
    private final String uz;
}
