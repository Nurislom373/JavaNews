package org.khasanof.basicValues.types;

import jakarta.persistence.*;
import org.hibernate.Length;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.type.YesNoConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    @Lob
    private String lob;

    // Length.LONG yozib ketsa LONG length bo'lgan VARCHAR JDBC database saqlab ketadi.
    @Column(length = Length.LONG)
    private String textFirstWay;
    @JdbcTypeCode(java.sql.Types.LONGVARCHAR)
    private String textSecondWay;

}

