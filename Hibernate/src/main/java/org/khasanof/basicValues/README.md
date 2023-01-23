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

# Formula Annotation

# Column Annotation

# Types

### BLOB and CLOB types

+ BLOB - Stores any kind of data in binary format. Typically used for multimedia files such as images and videos.
+ CLOB - Used for large strings or documents that use the database character set exclusively.
+ NCLOB - Stores string data in National Character Set format.

<hr/>

+ BLOB - data type har qanday ma'lumotlarni binary formatda saqlaydi. Odat Rasm va Video kabi filelar saqlash uchun ishlatiladi.
+ CLOB - data type katta stringlarni yoki documentlarni saqlash uchun ishlatiladi.
+ NCLOB - data type National characterlardan iborat stringlarni saqlaydi.