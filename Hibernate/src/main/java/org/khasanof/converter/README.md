# AttributeConverter

AttributeConverter ma'lum bir Java Objectni JDBC typega map qilish yani o'zgartirish uchun ishlatiladi.
JPA Provider tomonidan convertToDatabaseColumn method INSERT yoki UPDATE bayonatini bajarishdan oldin chaqiriladi.
convertToDatabaseColumn method Object attributeni oladi va tegishli jadval columiga o'rnatilishi kerak bo'lgan qiymatni qaytaradi. 

# Basic Annotation

Basic Annotatsiyasi 2ta atributni o'z ichiga oladi.

- <strong>optional</strong> - boolean (defaults to true)
Ushbu atribut nulllarga ruxsat beradimi yoki yo'qligini aniqlaydi.
- <strong>fetch</strong> - FetchType (defaults to EAGER)
Bu atribut qiymatni EAGER yoki LAZY olish kerakligini aniqlaydi.