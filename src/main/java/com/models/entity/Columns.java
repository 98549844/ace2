package com.models.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: Columns
 * @Date: 6/12/2021 4:26 上午
 * @Author: garlam
 * @Description:
 */


public class Columns {
    private static final Logger log = LogManager.getLogger(Columns.class.getName());


    private String tableCatalog = "";
    private String tableSchema = "";
    private String tableName = "";
    private String columnName = "";
    //  TABLE_CATALOG            varchar(512)        default '' not null,
    //  TABLE_SCHEMA             varchar(64)         default '' not null,
    //  TABLE_NAME               varchar(64)         default '' not null,
    //  COLUMN_NAME              varchar(64)         default '' not null,
    private long ordinalPosition = 0;
    private String columnDefault;
    private String isNullable = "";
    private String dataType = "";
    private long characterMaximumLength;
    private long characterOctetLength;

    //  ORDINAL_POSITION         bigint(21) unsigned default 0  not null,
    //  COLUMN_DEFAULT           longtext                       null,
    //  IS_NULLABLE              varchar(3)          default '' not null,
    //  DATA_TYPE                varchar(64)         default '' not null,
    //  CHARACTER_MAXIMUM_LENGTH bigint(21) unsigned            null,
    //  CHARACTER_OCTET_LENGTH   bigint(21) unsigned            null,

    private long numericPrecision;
    private long numericScale;
    private long dateTimePrecision;
    private String characterSetName;


    //  NUMERIC_PRECISION        bigint(21) unsigned            null,
    //  NUMERIC_SCALE            bigint(21) unsigned            null,
    //  DATETIME_PRECISION       bigint(21) unsigned            null,
    //  CHARACTER_SET_NAME       varchar(32)                    null,


    private String collationName;
    private String columnType = "";
    private String columnKey = "";
    private String extra = "";
    private String privileges = "";

    //  COLLATION_NAME           varchar(32)                    null,
    //  COLUMN_TYPE              longtext            default '' not null,
    //   COLUMN_KEY               varchar(3)          default '' not null,
    //   EXTRA                    varchar(30)         default '' not null,
//    PRIVILEGES               varchar(80)         default '' not null,

    private String columnComment = "";
    private String isGenerated = "";
    private String generationExpression;
    //  COLUMN_COMMENT           varchar(1024)       default '' not null,
    //  IS_GENERATED             varchar(6)          default '' not null,
    //  GENERATION_EXPRESSION    longtext                       null


    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(long numericScale) {
        this.numericScale = numericScale;
    }

    public long getDateTimePrecision() {
        return dateTimePrecision;
    }

    public void setDateTimePrecision(long dateTimePrecision) {
        this.dateTimePrecision = dateTimePrecision;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(String isGenerated) {
        this.isGenerated = isGenerated;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }
}

