package org.khasanof.basicValues.types;

import jakarta.persistence.*;
import org.hibernate.Length;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Nationalized;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.type.YesNoConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLType;
import java.time.*;
import java.util.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 3:54 PM
 * <br/>
 * Package: org.khasanof.basicValues.types
 */
public class Types {

    /*
        Enum Type ORDINAL qilib databasega saqlasak 0, 1 raqam ko'rinishida saqlaydi.

        0 - LAND_LINE
        1 - MOBILE

        yani tartib boyicha raqam beriladi.
     */
    @Enumerated(EnumType.ORDINAL)
    private PhoneType ordinalEnum;

    /*
        Enum Type STRING bo'ladigan bo'lsa string qilib databasega saqlab ketadi.

        'LAND_LINE', 'MOBILE' ko'rinishida databasega saqlaydi.
     */
    @Enumerated(EnumType.STRING)
    private PhoneType stringEnum;


    // boolean typeni hibernate databasega BIT yoki BOOLEAN ko'rinishida saqlaydi.
    @Basic
    private boolean implicit;

    /*
        YesNoConverter Hibernate tomonidan yozilgan AttributeConverter
        AttributeConverter boolean valuesiga qarab database yes or no qilib saqlab ketadi.

        // databasega BOOLEAN typeda saqlanmaydi. CHAR yoki NCHAR qilib saqlab ketadi.
        boolean valueni 'Y' or 'N' encode qiladi.
     */
    @Basic
    @Convert(converter = YesNoConverter.class)
    private boolean convertedYesNo;

    /*
        TrueFalseConverter classi ham Hibernate tomonidan yozilgan tayyor AttributeConverter
        boolean valuesiga qarab databasega 'T' yoki 'F' saqlab ketadi.

        // databasega BOOLEAN typeda saqlanmaydi. CHAR yoki NCHAR qilib saqlab ketadi.
     */
    @Basic
    @Convert(converter = TrueFalseConverter.class)
    private boolean convertedTrueFalse;

    /*
        NumericBooleanConverter classi ham Hibernate tomonidan yozilgan tayyor AttributeConverter
        boolean valuesiga qarab databasega '0' yoki '1' saqlab ketadi.

        // databasega BOOLEAN typeda saqlanmaydi. TINYINT qilib saqlab ketadi.
     */
    @Basic
    @Convert(converter = NumericBooleanConverter.class)
    private boolean convertedNumeric;


    // Hibernate Byte/byte JDBC TINYINT typega aylantirib saqlab ketadi.
    private Byte wrapperByte;
    private byte primitiveByte;

    // Hibernate Short/short JDBC SMALLINT typega aylantirib saqlab ketadi.
    private Short wrapperShort;
    private short primitiveShort;

    // Hibernate Integer/int JDBC INTEGER typega aylantiribi saqlab ketadi.
    private Integer wrapperInteger;
    private int primitiveInteger;

    // Hibernate Long/long JDBC BIGINT typega aylantirib saqlab ketadi.
    private Long wrapperLong;
    private long primitiveLong;

    // Hibernate BigInteger JDBC NUMERIC typega aylantirib saqlab ketadi.
    private BigInteger bigInteger;

    // Hibernate Double/double JDBC DOUBLE, FLOAT, REAl yoki NUMERIC lardan biriga aylantirib saqlab ketadi.
    private Double wrapperDouble;
    private double primitiveDouble;

    // Hibernate Float/float JDBC FLOAT, REAL yoki NUMERIC lardan biriga aylantirib saqlab ketadi.
    private Float wrapperFloat;
    private float primitiveFloat;

    // BigDecimal JDBC NUMERIC ga aylantirib saqlab ketadi.
    private BigDecimal bigDecimal;

    // Character JDBC CHAR ga aylantirib saqlab ketadi.
    private Character wrapperCharacter;
    private char primitiveCharacter;


    // String JDBC VARCHAR aylantirib saqlab ketadi.
    private String string;

    // String @Lob annotatsiyani qo'yib ketsak JDBC CLOB type aylantiridi.
    // @Lob annotatsiyasini faqat String bilan ishlatiladi.
    @Lob
    private String lob;

    // Length.LONG yozib ketsa LONG length bo'lgan VARCHAR JDBC database saqlab ketadi.
    @Column(length = Length.LONG)
    private String textFirstWay;
    @JdbcTypeCode(java.sql.Types.LONGVARCHAR)
    private String textSecondWay;
    @Nationalized
    private String nstring;
    @Lob
    @Nationalized
    private String nclobString;

    // Hibernate Character[]/char[] JDBC VARCHAR ga aylantirib saqlab ketadi.
    // @Lob annotatsiyasidan foydalangan holda CLOB aylantirib saqlab ketishimiz mumkin.
    private Character[] wrapperCharSequence;
    private char[] primitiveCharSequence;


    // Hibernate CLOB type
    /*
        CLOB interface SQL data typeni alternative.

        CLOB data type asosan katta textlarni saqlash uchun ishlatiladi.
        Hibernate NClob/Clob JDBC NCLOB va CLOB typega aylantirib saqlab ketadi.
     */
    private NClob nClob;
    private Clob clob;

    /*
        Hibernate Byte[]/byte[] JDBC VARBINARY typega aylantirib saqlab ketadi.
     */
    private Byte[] primitiveByteSequence;
    private byte[] wrapperByteSequence;

    /*
        Hibernate Byte[]/byte[] JDBC (materialized) BLOB typega aylantirib saqlab ketadi.

        Large Objects - (LOB) type katta hajmdagi ma'lumotlarni saqlash uchun mo'ljallangan.
        LOB - data type databaseni konfiguratsiyasiga qarab terabaytgacha bo'lishi mumkin.
     */
    @Lob
    private Byte[] primitiveByteLob;
    @Lob
    private byte[] wrapperByteLob;

    /*
        Hibernate java.sql Package dagi Blob interface SQL databaselardagi BLOB type alternative

        Blob asosan binary data yani fileni contentni saqlash uchun ishlatiladi.
        Blob interfacedan foydalanganimizda @Lob annotatsiyasini qoyishimiz kerak!
        Aks holda exception tashlaydi.
     */
    @Lob
    private Blob blobType;

    /*
        Duration data type Hibernate INTERVAL_SECOND SQL type o'tkazib saqlab ketadi.
         Kerak bo'lganda NUMERIC typega qaytaradi.
     */
    private Duration duration;


    /*
        Hibernate Instant/LocalDateTime classlarni TIMESTAMP ga aylantirib saqlab ketadi.
     */
    private Instant instant;
    private LocalDateTime localDateTime;


    /*
        Hibernate LocalDate classini DATE ga aylantirib saqlab ketadi.
     */
    private LocalDate date;


    /*
        Hibernate LocalTime classini TIME ga aylantirib saqlab ketadi.
     */
    private LocalTime localTime;


    /*
        Hibernate OffsetDateTime classni TIMESTAMP yoki TIMESTAMP_WITH_TIMEZONE ga aylantirib saqlab ketadi.
     */
    private OffsetDateTime offsetDateTime;


    /*
        Hibernate OffsetTime classini TIME yoki TIME_WITH_TIMEZONE ga aylantirib saqlab ketadi.
     */
    private OffsetTime offsetTime;


    /*
        Hibernate TimeZone classini VARCHAR ga aylantirib saqlab ketadi.
     */
    private TimeZone timeZone;


    /*
        Hibernate ZonedDateTime classini TIMESTAMP yoki TIMESTAMP_WITH_TIMEZONE ga aylantirib saqlab ketadi.
     */
    private ZonedDateTime zonedDateTime;


    /*
        Hibernate ZoneOffset classini VARCHAR ga aylantirib saqlab ketadi.
     */
    private ZoneOffset zoneOffset;


    /*
        Ushbu pastda e'lon qilingan 3ta classlarni Hibernate VARCHAR ga aylantirib saqlab ketadi.
     */
    private Class<?> clazz;
    private Currency currency;
    private Locale locale;


    /*
        UUID Hibernate databasegayam UUID qilib saqlab ketadi.
     */
    private UUID uuid;


    /*
        Hibernate InetAddress classini INET typega aylantirib saqlab ketadi.
     */
    private InetAddress address;


    /*
        Hibernate Ushbu Mapni JSON typega aylantirib saqlab ketadi.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> payload;


    /*
        Hibernate Collection classni subclasslarni ARRAY typega aylantirib saqlab ketadi.
     */
    private List<Short> list;
    private SortedSet<Short> sortedSet;





}

